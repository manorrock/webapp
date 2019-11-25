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
package com.manorrock.piranha.test.snoop;

import cloud.piranha.DefaultDirectoryResource;
import cloud.piranha.DefaultWebApplication;
import com.manorrock.piranha.test.utils.TestHttpServletRequest;
import com.manorrock.piranha.test.utils.TestHttpServletResponse;
import java.io.File;
import javax.servlet.http.Cookie;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The JUnit tests for the SnoopServlet class.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class SnoopServletTest {

    /**
     * Test GET method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testGetMethod() throws Exception {
        DefaultWebApplication webApp = new DefaultWebApplication();

        TestHttpServletRequest request = new TestHttpServletRequest();
        request.setWebApplication(webApp);
        request.setWebApplication(webApp);

        TestHttpServletResponse response = new TestHttpServletResponse();

        webApp.linkRequestAndResponse(request, response);

        SnoopServlet servlet = new SnoopServlet();
        servlet.service(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(new String(response.getResponseBody()).contains("Snoop"));

        webApp.unlinkRequestAndResponse(request, response);
    }

    /**
     * Test GET method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testGetMethod2() throws Exception {
        DefaultWebApplication webApp = new DefaultWebApplication();
        webApp.addResource(new DefaultDirectoryResource(new File("src/main/webapp")));
        webApp.addServletMapping("Snoop", "/Snoop");
        webApp.addServlet("Snoop", "com.manorrock.piranha.test.snoop.SnoopServlet");
        webApp.initialize();
        webApp.start();

        TestHttpServletRequest request = new TestHttpServletRequest();
        request.setWebApplication(webApp);
        request.setServletPath("/Snoop");
        request.setWebApplication(webApp);
        request.setParameter("Snoop", new String[] { "Snoop" });
        request.setAttribute("Snoop", "Snoop");
        Cookie cookie = new Cookie("COOKIE", "COOKIE");
        request.setCookies(new Cookie[] { cookie });
        TestHttpServletResponse response = new TestHttpServletResponse();

        webApp.service(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(new String(response.getResponseBody()).contains("Snoop"));
    }

    /**
     * Test POST method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testPostMethod() throws Exception {
        DefaultWebApplication webApp = new DefaultWebApplication();
        webApp.addResource(new DefaultDirectoryResource(new File("src/main/webapp")));
        webApp.addServletMapping("Snoop", "/Snoop");
        webApp.addServlet("Snoop", "com.manorrock.piranha.test.snoop.SnoopServlet");
        webApp.initialize();
        webApp.start();

        TestHttpServletRequest request = new TestHttpServletRequest();
        request.setWebApplication(webApp);
        request.setServletPath("/Snoop");
        request.setWebApplication(webApp);
        request.setParameter("Snoop", new String[] { "Snoop" });
        request.setAttribute("Snoop", "Snoop");
        request.setMethod("POST");
        Cookie cookie = new Cookie("COOKIE", "COOKIE");
        request.setCookies(new Cookie[] { cookie });
        TestHttpServletResponse response = new TestHttpServletResponse();

        webApp.service(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(new String(response.getResponseBody()).contains("Snoop"));
    }
}
