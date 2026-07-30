package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AssetMapResponse extends APIResponse{

    Map<?, List<Asset>> asmap;
}
