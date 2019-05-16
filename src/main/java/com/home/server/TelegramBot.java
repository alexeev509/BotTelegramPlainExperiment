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
        String message = update.getMessage().getText();
        System.out.println(message);
        try {
            int seconds=Integer.parseInt(message);
            Thread.sleep(1000*seconds);
        }catch (Exception e){
            
        }

        sendMsg(update.getMessage().getChatId().toString(), message);
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
