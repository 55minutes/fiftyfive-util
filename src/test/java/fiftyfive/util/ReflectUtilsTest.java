/**
 * Copyright 2010 55 Minutes (http://www.55minutes.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fiftyfive.util;

import org.junit.Assert;
import org.junit.Test;

public class ReflectUtilsTest
{
    @Test
    public void testInvokeZeroArgMethod()
    {
        Bean bean = new Bean();
        
        Assert.assertNull(ReflectUtils.invokeZeroArgMethod(bean, "voidMethod"));
        Assert.assertEquals(
            "public",
            ReflectUtils.invokeZeroArgMethod(bean, "publicMethod")
        );
        Assert.assertEquals(
            "protected",
            ReflectUtils.invokeZeroArgMethod(bean, "protectedMethod")
        );
        Assert.assertEquals(
            "private",
            ReflectUtils.invokeZeroArgMethod(bean, "privateMethod")
        );
        Assert.assertEquals(
            "default",
            ReflectUtils.invokeZeroArgMethod(bean, "defaultMethod")
        );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvokeZeroArgMethod_arg()
    {
        ReflectUtils.invokeZeroArgMethod(new Bean(), "methodWithArg");
    }
    
    @Test(expected=TestException.class)
    public void testInvokeZeroArgMethod_exception()
    {
        ReflectUtils.invokeZeroArgMethod(new Bean(), "exceptionalMethod");
    }

    private static class Bean
    {
        public void voidMethod()
        {
        }
    
        public String publicMethod()
        {
            return "public";
        }
    
        protected String protectedMethod()
        {
            return "protected";
        }
    
        private String privateMethod()
        {
            return "private";
        }
    
        String defaultMethod()
        {
            return "default";
        }
    
        public String methodWithArg(String arg)
        {
            return "arg";
        }
    
        public void exceptionalMethod()
        {
            throw new TestException();
        }
    }
    
    private static class TestException extends RuntimeException
    {
    }
}
