/**
 * Copyright 2014 55 Minutes (http://www.55minutes.com)
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

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class VersionTest
{
    /**
     * Inspect the slf4j-api jar and verify its version information.
     */
    @Test
    public void testVersionOfJar()
    {
        Date now = new Date();
        Version v = Version.ofJar(org.slf4j.Logger.class);
        
        Assert.assertEquals("1.6.1", v.getVersion());
        Assert.assertEquals("slf4j-api", v.getTitle());
        Assert.assertTrue(v.getModifiedDate().before(now));
        
        // slf4j-api 1.6.1 was built in July 2010
        Calendar expected = Calendar.getInstance();
        expected.set(Calendar.YEAR, 2010);
        expected.set(Calendar.MONTH, 6);
        expected.set(Calendar.DATE, 1);
        
        Calendar mod = Calendar.getInstance();
        mod.setTime(v.getModifiedDate());
        Assert.assertTrue(mod.after(expected));
    }
}
