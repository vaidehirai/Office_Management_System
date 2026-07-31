package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PriMapResponse extends APIResponse{

    private Map<? extends Number, ? extends Number> resultmap;
}
