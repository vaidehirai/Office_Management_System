package com.oms.officemanagementsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    public AssetRepository repository;

    public AssetService(AssetRepository repository){
        this.repository=repository;
    }

    public ResponseEntity<APIResponse> getAssetDetailsAssetid(Integer id){
        Optional<Asset> a1= repository.findById(id);

        if(a1.isPresent()){
            AssetResponse ar=new AssetResponse();
            Asset ast=a1.get();

            ar.setAsset(ast);
            ar.setMessage("Asset found successfully");
            return ResponseEntity.status(HttpStatus.OK).body(ar);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Invalid id"));
        }
    }

    public ResponseEntity<APIResponse> getAssetDetailsAssetname(String assetname){
        ArrayList<Asset> ala=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Asset> a1=repository.findById(i);
            Asset a2=a1.get();
            if(a2.getAssetname().equals(assetname)){
                ala.add(a2);
            }
        }

        if(ala.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No assets found of given asset name");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            AssetListResponse ar=new AssetListResponse();
            ar.setAssetlist(ala);
            ar.setMessage("Assets of given asset name found");
            return ResponseEntity.status(HttpStatus.OK).body(ar);
        }
    }

    public ResponseEntity<APIResponse> getAssetDetailsAssetvalue(Integer assetvalue){
        ArrayList<Asset> ala=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Asset> a1=repository.findById(i);
            Asset a2=a1.get();

            if(a2.getAssetvalue().equals(assetvalue)){
                ala.add(a2);
            }
        }

        if(ala.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No assets found of given asset value");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            AssetListResponse ar=new AssetListResponse();
            ar.setAssetlist(ala);
            ar.setMessage("Assets of given asset value found");
            return ResponseEntity.status(HttpStatus.OK).body(ar);
        }
    }

    //Details of assets that an employee owns
    public ResponseEntity<APIResponse> getAssetDetailsOfEmpid(Integer empid){
        ArrayList<Asset> ala=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Asset> a1=repository.findById(i);
            Asset a2=a1.get();
            if(a2.getEmpid()==empid){
                ala.add(a2);
            }
        }

        if(ala.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No assets found of given employee id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            AssetListResponse ar=new AssetListResponse();
            ar.setAssetlist(ala);
            ar.setMessage("Assets of given employee id found");
            return ResponseEntity.status(HttpStatus.OK).body(ar);
        }
    }

    public ResponseEntity<AssetListResponse> getAllAssets(){

        List<Asset> la=repository.findAll();
        ArrayList<Asset> ala=new ArrayList<>(la);

        AssetListResponse ar=new AssetListResponse();
        ar.setAssetlist(ala);
        ar.setMessage("All assets found");
        return ResponseEntity.status(HttpStatus.OK).body(ar);
    }

    public  ResponseEntity<APIResponse> createAsset(Asset asset){

        if(asset.assetid==null || asset.assetname==null || asset.assetvalue==null || asset.empid==null){
            APIResponse apir=new APIResponse();
            apir.setMessage("Missing asset details. Asset details could not be inserted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            repository.save(asset);
            AssetResponse ar=new AssetResponse();
            ar.setAsset(asset);
            ar.setMessage("New asset entry created");
            return ResponseEntity.status(HttpStatus.CREATED).body(ar);
        }
    }

    public ResponseEntity<APIResponse> updateAssetName(Asset asset){
        Optional<Asset> asset1=repository.findById(asset.assetid);

        APIResponse apir=new APIResponse();
        if(asset1.isPresent()){
            Asset asset2=asset1.get();

            asset2.setAssetid(asset.getAssetid());
            asset2.setAssetname(asset.getAssetname());
            asset2.setAssetvalue(asset.getAssetvalue());
            asset2.setEmpid(asset.getEmpid());
            repository.save(asset2);
            apir.setMessage("Asset with given id updated");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("Invalid asset id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> deleteAsset(Asset asset){
        Optional<Asset> asset1=repository.findById(asset.assetid);
        APIResponse apir=new APIResponse();

        if(asset1.isPresent()){
            repository.delete(asset1.get());
            apir.setMessage("Asset with given id deleted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("Invalid id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }
}

