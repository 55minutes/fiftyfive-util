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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Truncates strings using a configurable set of rules. For a reasonable
 * default ruleset, just use the empty constructor:
 * <pre>
 * String s = "My really long string that needs to be cut down to size.";
 * new TruncateHelper().truncate(s, 50); // "My really long string that needs to be cut down…"
 * </pre>
 */
public class TruncateHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(
        TruncateHelper.class
    );
    
    private String _suffix = "…";
    private boolean _trimFirst = true;
    private boolean _compressWhiteSpace = true;
    private int _breakWordLongerThan = 10;
    private Pattern _wordPattern = Pattern.compile("[^\\s\\-–—]");
    private Pattern _wordDelimeterPattern = Pattern.compile("[\\s\\-–—]");
    
    public TruncateHelper()
    {
        super();
    }
    
    public String truncate(String string, int maxLength)
    {
        if(null == string) return null;
        
        if(_trimFirst)
        {
            string = string.trim();
        }
        
        if(_compressWhiteSpace)
        {
            string = string.replaceAll("\\s{2,}", " ");
        }
        
        if(string.length() <= maxLength)
        {
            return string;
        }
        
        Pattern patt = Pattern.compile(String.format(
            "(?s)^%s(.{%d,%d}%s)%s.*",
            _trimFirst ? "\\s*" : "",
            maxLength - 1 - _breakWordLongerThan - _suffix.length(),
            maxLength - 1 - _suffix.length(),
            _wordPattern,
            _wordDelimeterPattern
        ));
        LOGGER.debug("Pattern is {}", patt);
        Matcher match = patt.matcher(string);
        if(match.matches())
        {
            return string.substring(0, match.end(1)) + _suffix;
        }
        LOGGER.debug("Pattern did not match");
        return string.substring(0, maxLength - 1) + _suffix;
    }
}
