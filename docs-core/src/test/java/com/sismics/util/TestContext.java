package com.sismics.util;
import org.junit.Test;
import com.sismics.util.context.ThreadLocalContext;

public class TestContext {
        @Test
        public void testAddasynch() {
            ThreadLocalContext cxt = ThreadLocalContext.get();
            cxt.addAsyncEvent(new Object());

    }
    
}
