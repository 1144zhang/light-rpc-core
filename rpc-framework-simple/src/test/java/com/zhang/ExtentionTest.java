package com.zhang;

import com.zhang.extension.ExtensionLoader;
import com.zhang.loadbalance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author: Zekun Fu
 * @date: 2025/6/8 19:58
 * @Description:
 */
@Slf4j
public class ExtentionTest {


    @Test
    public void testExtention() {
        LoadBalance loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension("loadBalanceNew");
    }
}
