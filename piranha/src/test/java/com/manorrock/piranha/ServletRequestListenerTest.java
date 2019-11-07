/*
 * Copyright (c) 2002-2019 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.piranha;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * The JUnit tests for testing everything related to the ServletRequestListener
 * API.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class ServletRequestListenerTest {

    /**
     * Test requestDestroyed method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testRequestDestroyed() throws Exception {
        DefaultWebApplication webApplication = new DefaultWebApplication();
        webApplication.addListener(new TestServletRequestListener());
        TestHttpServletRequest request = new TestHttpServletRequest();
        TestHttpServletResponse response = new TestHttpServletResponse();
        TestServletOutputStream outputStream = new TestServletOutputStream();
        response.setOutputStream(outputStream);
        outputStream.setResponse(response);
        webApplication.initialize();
        webApplication.start();
        webApplication.service(request, response);
        assertNotNull(webApplication.getAttribute("requestDestroyed"));
        webApplication.stop();
        webApplication.destroy();
    }

    /**
     * Test requestInitialized method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testRequestInitialized() throws Exception {
        DefaultWebApplication webApplication = new DefaultWebApplication();
        webApplication.addListener(new TestServletRequestListener());
        TestHttpServletRequest request = new TestHttpServletRequest();
        TestHttpServletResponse response = new TestHttpServletResponse();
        TestServletOutputStream outputStream = new TestServletOutputStream();
        response.setOutputStream(outputStream);
        outputStream.setResponse(response);
        webApplication.initialize();
        webApplication.start();
        webApplication.service(request, response);
        assertNotNull(webApplication.getAttribute("requestInitialized"));
        webApplication.stop();
        webApplication.destroy();
    }

    public class TestServletRequestListener implements ServletRequestListener {

        /**
         * Handle request destroyed event.
         *
         * @param event the event.
         */
        @Override
        public void requestDestroyed(ServletRequestEvent event) {
            event.getServletContext().setAttribute("requestDestroyed", true);
        }

        /**
         * Handle request initialized event.
         *
         * @param event the event.
         */
        @Override
        public void requestInitialized(ServletRequestEvent event) {
            event.getServletContext().setAttribute("requestInitialized", true);
        }
    }
}
