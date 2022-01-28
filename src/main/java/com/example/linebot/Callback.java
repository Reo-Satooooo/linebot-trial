package com.example.linebot;

import com.example.linebot.replier.Follow;
import com.example.linebot.replier.Parrot;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    // フォローイベントへの対応
    @EventMapping
    public Message handleFollow(FollowEvent event){
        // 実際はこんタイミングでフォロワーのユーザーIDをデータベースに格納しておくなどする
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられた時に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event){
        Parrot parrot = new Parrot(event);
        return parrot.reply();
    }
}