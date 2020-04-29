package com.liaojl.test.rpc.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class Issue {
    String title;
    String body;
    List<String> assignees;
    int milestone;
    List<String> labels;
}