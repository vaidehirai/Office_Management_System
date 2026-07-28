package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LongResponse extends APIResponse{

    private long count;
}
