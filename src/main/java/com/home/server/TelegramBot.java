package com.home.server;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class TelegramBot extends TelegramLongPollingBot {

    TextTimeParser textTimeParser=new TextTimeParser();

    public static void main(String[] args) {
//        String proxyHost = "213.136.69.212";
//        int proxyPort = 1234;
//        int timeout = 75 * 1000;
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        TelegramLongPollingBot bot = new TelegramBot();

//        RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(new HttpHost(proxyHost, proxyPort))
//                        .setSocketTimeout(timeout)
//                        .setConnectionRequestTimeout(timeout)
//                        .setConnectTimeout(timeout)
//                        .build();
//        bot.getOptions().setRequestConfig(requestConfig);




        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        final String message = update.getMessage().getText();
        final Update update1=update;
        System.out.println(message);
        new Thread(new Runnable() {
            public void run() {
                try {
                    int seconds=Integer.parseInt(message);
                    Thread.sleep(1000*seconds);
                    sendMsg(update1.getMessage().getChatId().toString(), "Это ответ на ваше сообщени'"+message+"'");
                }catch (Exception e){

                    try {
                        Thread.sleep(1000*textTimeParser.parseString(message));
                        sendMsg(update1.getMessage().getChatId().toString(), "Это ответ на ваше сообщени'"+message+"'");
                    } catch (Exception e1) {
                        sendMsg(update1.getMessage().getChatId().toString(), "I don't understand you, bro");
                    }
                }


            }
        }).start();

    }

    public String getBotUsername() {
        return "KrasavchegBot";
    }

    public String getBotToken() {
        return "816690440:AAHOQrZlo9Vc79WBQGulpNniAZ23GYfipRE";
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
           // log.log(Level.SEVERE, "Exception: ", e.toString());
            System.out.println("Exception: "+"\n"+ e.toString());
        }
    }

}
