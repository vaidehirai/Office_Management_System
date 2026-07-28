package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StResponse extends APIResponse {

    private List<String> items;
}
