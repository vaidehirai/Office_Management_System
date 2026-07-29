package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service){
        this.service=service;
    }

    @GetMapping("/assetid/{id}")
    public ResponseEntity<APIResponse> getAssetDetailsAssetid(@PathVariable Integer id){
        return service.getAssetDetailsAssetid(id);
    }

    @GetMapping("/assetname/{assetname}")
    public ResponseEntity<APIResponse> getAssetDetailsAssetname(@PathVariable String assetname){
        return service.getAssetDetailsAssetname(assetname);
    }

    @GetMapping("/assetvalue/{assetvalue}")
    public ResponseEntity<APIResponse> getAssetDetailsAssetvalue(@PathVariable Integer assetvalue){
        return service.getAssetDetailsAssetvalue(assetvalue);
    }

    @GetMapping("/empid/{empid}")
    public ResponseEntity<APIResponse> getAssetDetailsOfEmpid(@PathVariable Integer empid){
        return service.getAssetDetailsOfEmpid(empid);
    }

    @GetMapping("/all")
    public ResponseEntity<AssetListResponse> getAllAssets(){
        return service.getAllAssets();
    }

    @PostMapping
    public ResponseEntity<APIResponse> createAsset(@RequestBody Asset asset){
        return service.createAsset(asset);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateAssetName(@RequestBody Asset asset){
        return service.updateAssetName(asset);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteAsset(@RequestBody Asset asset){
        return service.deleteAsset(asset);
    }
}
