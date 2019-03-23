/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 - 2019
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.apex.bot;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SpamBot {

    private static final String BOT_NAME = "SpamBot";
    private static final Logger LOG = LoggerFactory.getLogger(SpamBot.class);

    public static void main(String[] args){
        TelegramSessionManager telegramSessionManager = new TelegramSessionManager();
        try {
            String content = new String(Files.readAllBytes(Paths.get("token.json")));
            JSONObject questions = new JSONObject(content);
            questions.toMap().get("token");
            telegramSessionManager.addPollingBot(new TelegramMessageHandler((String)questions.toMap().get("token"), BOT_NAME));
            telegramSessionManager.start();
            LOG.info("Bot started");
            while (true){
                Thread.sleep(500);
            }
        } catch (Exception e) {
            LOG.error("Something went wrong: "+ e.getCause().getMessage());
        } finally {
            telegramSessionManager.stop();
            LOG.info("Bot down");
        }
    }
}
