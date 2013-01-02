/**
 * Copyright 2013 55 Minutes (http://www.55minutes.com)
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
import org.junit.Before;
import org.junit.Test;

public class TruncateHelperTest
{
    TruncateHelper _helper;
    
    @Before
    public void setUp()
    {
        _helper = new TruncateHelper();
    }
    
    @Test
    public void testTruncate()
    {
        String s = "My really long string that needs to be cut down to size.";
        Assert.assertEquals(
            "My really long string that needs to be cut down…",
            _helper.truncate(s, 50)
        );
    }

    @Test
    public void testTruncate_giantWord()
    {
        String s = "Myreallylongstringthatneedstobecutdowntosize.";
        Assert.assertEquals(
            "Myreallylongstringthatneedsto…",
            _helper.truncate(s, 30)
        );
    }

    @Test
    public void testTruncate_apostrophe()
    {
        String s = "Breaking where is an apostrophe isn't a good idea.";
        Assert.assertEquals(
            "Breaking where is an apostrophe…",
            _helper.truncate(s, 36)
        );
    }

    @Test
    public void testTruncate_parenthesis()
    {
        String s = "Including trailing parenthesis (if possible) is good.";
        Assert.assertEquals(
            "Including trailing parenthesis (if possible)…",
            _helper.truncate(s, 46)
        );
    }

    @Test
    public void testTruncate_dash()
    {
        String s = "If we encounter dashes—like this—don't include them.";
        Assert.assertEquals(
            "If we encounter dashes—like this…",
            _helper.truncate(s, 35)
        );
    }

    @Test
    public void testTruncate_quotation()
    {
        String s = "It's ok to include \"quotation marks\" along with words.";
        Assert.assertEquals(
            "It's ok to include \"quotation marks\"…",
            _helper.truncate(s, 40)
        );
    }

    @Test
    public void testTruncate_longWord()
    {
        String s = "Just give up and break significantly long words.";
        Assert.assertEquals(
            "Just give up and break significan…",
            _helper.truncate(s, 34)
        );
    }
    
    @Test
    public void testTruncate_trim()
    {
        String s = "  Already short enough, when trimmed.  ";
        Assert.assertEquals(
            "Already short enough, when trimmed.",
            _helper.truncate(s, 35)
        );
    }

    @Test
    public void testTruncate_null()
    {
        Assert.assertEquals(null, _helper.truncate(null, 35));
    }
}
