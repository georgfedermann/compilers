package org.poormanscastle.studies.compilers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Created by georg on 21.12.15.
 */
public class TestUtils {
    public static InputStream getTestdataAsInputStream(String path) throws Exception {
        return new ByteArrayInputStream(IOUtils.toByteArray(TestUtils.class.getResourceAsStream(path)));
    }
}
