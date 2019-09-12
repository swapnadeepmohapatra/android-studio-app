package io.github.swapnadeepmohapatra.smartreply.chat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.swapnadeepmohapatra.smartreply.R;
import io.github.swapnadeepmohapatra.smartreply.model.Message;

class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private final List<Message> mMessagesList = new ArrayList<>();
    private boolean mEmulatingRemoteUser = false;

    public MessageListAdapter() {}

    @Override
    @NonNull
    public MessageListAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = mMessagesList.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessagesList.get(position).isLocalUser && !mEmulatingRemoteUser
                || !mMessagesList.get(position).isLocalUser && mEmulatingRemoteUser) {
            return R.layout.item_message_local;
        } else {
            return R.layout.item_message_remote;
        }
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    public void setMessages(List<Message> messages) {
        mMessagesList.clear();
        mMessagesList.addAll(messages);
        notifyDataSetChanged();
    }

    public boolean getEmulatingRemoteUser() {
        return this.mEmulatingRemoteUser;
    }

    public void setEmulatingRemoteUser(boolean emulatingRemoteUser) {
        this.mEmulatingRemoteUser = emulatingRemoteUser;
        notifyDataSetChanged();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView icon;
        private final TextView text;

        MessageViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.messageAuthor);
            text = itemView.findViewById(R.id.messageText);
        }

        private void bind(Message message) {
            icon.setImageDrawable(message.getIcon(icon.getContext()));
            text.setText(message.text);
        }
    }
}