/*
 *  Copyright (c) 2002-2018, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.servlet.DispatcherType;

/**
 * The WebFilter API.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebFilter {

    /**
     * Get the async supported flag.
     *
     * @return the async supported flag.
     */
    boolean asyncSupported() default false;

    /**
     * Get the description.
     *
     * @return the description.
     */
    String description() default "";

    /**
     * Get the dispatcher types.
     *
     * @return the dispatcher type.s
     */
    DispatcherType[] dispatcherTypes() default {DispatcherType.REQUEST};

    /**
     * Get the display name.
     *
     * @return the display name.
     */
    String displayName() default "";

    /**
     * Get the name.
     *
     * @return the name.
     */
    String filterName() default "";

    /**
     * Get the init parameters.
     *
     * @return the init parameters.
     */
    WebInitParam[] initParams() default {};

    /**
     * Get the large icon.
     *
     * @return the large icon.
     */
    String largeIcon() default "";

    /**
     * Get the servlet names.
     *
     * @return the servlet names.
     */
    String[] servletNames() default {};

    /**
     * Get the small icon.
     *
     * @return the small icon.
     */
    String smallIcon() default "";

    /**
     * Get the URL patterns.
     *
     * @return the URL patterns.
     */
    String[] urlPatterns() default {};

    /**
     * Get the URL patterns.
     *
     * @return the URL patterns.
     */
    String[] value() default {};

}
