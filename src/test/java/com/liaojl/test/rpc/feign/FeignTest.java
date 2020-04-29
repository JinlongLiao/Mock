package com.liaojl.test.rpc.feign;

import com.liaojl.test.rpc.feign.dto.Contributor;
import com.liaojl.test.rpc.feign.nct.GitHub;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FeignTest {
    private static final Logger log = LoggerFactory.getLogger(FeignTest.class);

    @Test
    public void feignTest() {
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");
        // Fetch and print a list of the contributors to this library.
        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getContributions() + " (" + contributor.getContributions() + ")");
        }
    }
}