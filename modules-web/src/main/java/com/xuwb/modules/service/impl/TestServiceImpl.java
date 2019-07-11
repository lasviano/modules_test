package com.xuwb.modules.service.impl;

import com.xuwb.modules.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public void testConfigPrint() {
        System.out.println("run testService.testConfigPrint");
    }
}
