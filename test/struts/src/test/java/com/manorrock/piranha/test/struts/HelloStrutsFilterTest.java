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
package com.manorrock.piranha.test.struts;

import cloud.piranha.DefaultDirectoryResource;
import cloud.piranha.DefaultLoggingManager;
import cloud.piranha.DefaultWebApplication;
import com.manorrock.piranha.test.utils.TestHttpServletRequest;
import com.manorrock.piranha.test.utils.TestHttpServletResponse;
import com.manorrock.piranha.test.utils.TestServletOutputStream;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The JUnit tests for the Struts 2 filter.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HelloStrutsFilterTest {

    /**
     * Test GET method.
     *
     * @throws Exception when a major error occurs.
     */
    @Test
    public void testGetMethod() throws Exception {
        DefaultWebApplication webApp = new DefaultWebApplication();
        webApp.setLoggingManager(new DefaultLoggingManager());
        webApp.addResource(new DefaultDirectoryResource(new File("src/main/webapp")));
        webApp.addFilter("struts2", "org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter");
        webApp.addFilterMapping("struts2", "*.action");
        webApp.addInitializer("com.manorrock.piranha.pages.jasper.JasperInitializer");
        webApp.initialize();
        webApp.start();

        TestHttpServletRequest request = new TestHttpServletRequest();
        request.setWebApplication(webApp);
        request.setServletPath("/HelloWorld.action");

        TestHttpServletResponse response = new TestHttpServletResponse();
        TestServletOutputStream outputStream = new TestServletOutputStream();
        response.setOutputStream(outputStream);
        outputStream.setResponse(response);

        webApp.service(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(new String(response.getResponseBody()).contains("Hello World Struts"));
    }
}
