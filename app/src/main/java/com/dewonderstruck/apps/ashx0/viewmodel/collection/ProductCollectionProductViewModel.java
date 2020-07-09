package com.dewonderstruck.apps.ashx0.viewmodel.collection;

import com.dewonderstruck.apps.Config;
import com.dewonderstruck.apps.ashx0.repository.collection.ProductCollectionRepository;
import com.dewonderstruck.apps.ashx0.utils.AbsentLiveData;
import com.dewonderstruck.apps.ashx0.utils.Utils;
import com.dewonderstruck.apps.ashx0.viewmodel.common.PSViewModel;
import com.dewonderstruck.apps.ashx0.viewobject.Product;
import com.dewonderstruck.apps.ashx0.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProductCollectionProductViewModel extends PSViewModel {


    // for ProductCollectionHeader

    private final LiveData<Resource<List<Product>>> productCollectionProductListData;
    private MutableLiveData<ProductCollectionProductViewModel.TmpDataHolder> productCollectionProductListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageLoadingStateData;
    private MutableLiveData<ProductCollectionProductViewModel.TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    //region Constructor

    @Inject
    ProductCollectionProductViewModel(ProductCollectionRepository repository) {
        Utils.psLog("Inside ProductViewModel");


        // Latest ProductCollectionHeader List
        productCollectionProductListData = Transformations.switchMap(productCollectionProductListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.getProductCollectionProducts(Config.API_KEY, obj.limit, obj.offset, obj.id);
        });

        nextPageLoadingStateData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageProductCollectionProduct(obj.limit, obj.offset, obj.id);
        });


    }

    //endregion

    //region ProductCollectionHeader

    // Get ProductCollectionHeader
    public void setProductCollectionProductListObj(String limit, String offset, String id) {
        if (!isLoading) {
            ProductCollectionProductViewModel.TmpDataHolder tmpDataHolder = new ProductCollectionProductViewModel.TmpDataHolder();
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.id = id;
            productCollectionProductListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Product>>> getProductCollectionProductListData() {
        return productCollectionProductListData;
    }

    //Get Latest ProductCollectionHeader Next Page
    public void setNextPageLoadingStateObj(String limit, String offset, String id) {

        if (!isLoading) {
            ProductCollectionProductViewModel.TmpDataHolder tmpDataHolder = new ProductCollectionProductViewModel.TmpDataHolder();
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            tmpDataHolder.id = id;
            nextPageLoadingStateObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageLoadingStateData;
    }

    //endregion

    //region Holder

    class TmpDataHolder {

        public String offset = "";
        String limit = "";
        String id = "";
        public Boolean isConnected = false;
        public String shopId = "";

    }

    //endregion
}
