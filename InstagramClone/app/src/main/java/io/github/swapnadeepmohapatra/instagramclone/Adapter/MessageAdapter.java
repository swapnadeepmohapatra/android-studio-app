package io.github.swapnadeepmohapatra.instagramclone.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.instagramclone.Model.Message;
import io.github.swapnadeepmohapatra.instagramclone.R;

public class MessageAdapter extends ArrayAdapter<Message> {
    public MessageAdapter(@NonNull Context context, int resource, List<Message> objects) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.message_layout, parent, false);
        }

        CircleImageView userImageView = convertView.findViewById(R.id.userProfilePhoto);
        ImageView photoImageView = convertView.findViewById(R.id.messagePhoto);
        TextView messageTextView = convertView.findViewById(R.id.userMessage);
        TextView authorTextView = convertView.findViewById(R.id.userName);
        TextView time = convertView.findViewById(R.id.messageTime);
        TextView date = convertView.findViewById(R.id.messageDate);
        TextView status = convertView.findViewById(R.id.userStatus);
        ImageView share = convertView.findViewById(R.id.shareButton);
        CheckBox like = convertView.findViewById(R.id.likeCheckbox);

        final Message message = getItem(getCount() - position - 1);

        Glide.with(photoImageView.getContext())
                .load(Objects.requireNonNull(message).getMessagePhotoUrl())
                .into(photoImageView);
        messageTextView.setText(message.getMessage());
        status.setText(message.getUserStatus());
        time.setText(message.getTime());
        date.setText(message.getDate());
        authorTextView.setText(message.getUserName());
        Glide.with(userImageView.getContext()).load(message.getUserPhotoUrl()).into(userImageView);

        photoImageView.setLongClickable(true);
        photoImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CharSequence options[] = new CharSequence[]{
                        "Yes", "No"
                };
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Do you want to download this image ?");
                alertDialog.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            downloadImage();
                        }
                    }
                });
                alertDialog.show();
                return true;
            }

            private void downloadImage() {
                Glide.with(getContext())
                        .asBitmap()
                        .load(message.getMessagePhotoUrl())
                        .into(new SimpleTarget<Bitmap>() {
                                  @Override
                                  public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                                      Intent intent = new Intent(Intent.ACTION_VIEW);
                                      Uri uri = saveWallpaperAndGetUri(resource);
                                      if (uri != null) {
                                          intent.setDataAndType(uri, "image/*");
                                          getContext().startActivity(Intent.createChooser(intent, "Instagram Clone"));
                                      }
                                  }
                              }
                        );
            }

            private Uri saveWallpaperAndGetUri(Bitmap bitmap) {
                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat
                            .shouldShowRequestPermissionRationale((Activity) getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                        intent.setData(uri);
                        getContext().startActivity(intent);
                    } else {
                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                    }
                    return null;
                }
                File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Instagram Clone");
                folder.mkdirs();
                File file = new File(folder, message.getMessage().trim().replace(" ", "").toLowerCase() + message.getUserName().trim().replace(" ", "").toLowerCase() + ".jpg");
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                    return Uri.fromFile(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Getting Image to share...");
                progressDialog.show();

                Glide.with(getContext())
                        .asBitmap()
                        .load(message.getMessagePhotoUrl())
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("image/*");
                                intent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(resource));
                                intent.putExtra(Intent.EXTRA_TEXT, "Shared by Instagram Clone App");
                                getContext().startActivity(Intent.createChooser(intent, "Instagram Clone"));
                                progressDialog.dismiss();
                            }
                        });
            }

            private Uri getLocalBitmapUri(Bitmap bmp) {
                Uri bmpUri = null;
                try {
                    File file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            "WallpaperHubImages" + System.currentTimeMillis() + ".png");
                    FileOutputStream out = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 99, out);
                    out.close();
                    bmpUri = Uri.fromFile(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmpUri;
            }
        });

        return convertView;
    }


}
