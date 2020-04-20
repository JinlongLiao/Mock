/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.liaojl.netty.websocket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Generates the demo HTML page which is served at http://localhost:8080/
 */
public final class WebSocketServerIndexPage {

    public static ByteBuf getContent(String webSocketLocation) {
        return Unpooled.copiedBuffer(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "Netty WebSocket 时间服务器\n" +
                        "</head>\n" +
                        "<br>\n" +
                        "<body>\n" +
                        "<br>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "var socket;\n" +
                        "if (!window.WebSocket) \n" +
                        "{\n" +
                        "\twindow.WebSocket = window.MozWebSocket;\n" +
                        "}\n" +
                        "if (window.WebSocket) {\n" +
                        "\tsocket = new WebSocket(\"" + webSocketLocation + "\");\n" +
                        "\tsocket.onmessage = function(event) {\n" +
                        "\t\tvar ta = document.getElementById('responseText');\n" +
                        "\t\tta.value=\"\";\n" +
                        "\t\tta.value = event.data\n" +
                        "\t};\n" +
                        "\tsocket.onopen = function(event) {\n" +
                        "\t\tvar ta = document.getElementById('responseText');\n" +
                        "\t\tta.value = \"打开WebSocket服务正常，浏览器支持WebSocket!\";\n" +
                        "\t};\n" +
                        "\tsocket.onclose = function(event) {\n" +
                        "\t\tvar ta = document.getElementById('responseText');\n" +
                        "\t\tta.value = \"\";\n" +
                        "\t\tta.value = \"WebSocket 关闭!\"; \n" +
                        "\t};\n" +
                        "}\n" +
                        "else\n" +
                        "\t{\n" +
                        "\talert(\"抱歉，您的浏览器不支持WebSocket协议!\");\n" +
                        "\t}\n" +
                        "\n" +
                        "function send(message) {\n" +
                        "\tif (!window.WebSocket) { return; }\n" +
                        "\tif (socket.readyState == WebSocket.OPEN) {\n" +
                        "\t\tsocket.send(message);\n" +
                        "\t}\n" +
                        "\telse\n" +
                        "\t\t{\n" +
                        "\t\t  alert(\"WebSocket连接没有建立成功!\");\n" +
                        "\t\t}\n" +
                        "}\n" +
                        "</script>\n" +
                        "<form onsubmit=\"return false;\">\n" +
                        "<input type=\"text\" name=\"message\" value=\"Netty最佳实践\"/>\n" +
                        "<br><br>\n" +
                        "<input type=\"button\" value=\"发送WebSocket请求消息\" onclick=\"send(this.form.message.value)\"/>\n" +
                        "<hr color=\"blue\"/>\n" +
                        "<h3>服务端返回的应答消息</h3>\n" +
                        "<textarea id=\"responseText\" style=\"width:500px;height:300px;\"></textarea>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>", CharsetUtil.UTF_8);
    }

    private WebSocketServerIndexPage() {
        // Unused
    }
}
