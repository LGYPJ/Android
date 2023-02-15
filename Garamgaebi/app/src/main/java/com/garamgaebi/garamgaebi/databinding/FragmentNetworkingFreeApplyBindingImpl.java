package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNetworkingFreeApplyBindingImpl extends FragmentNetworkingFreeApplyBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_network_free_apply_info, 8);
        sViewsWithIds.put(R.id.activity_network_free_date_tv, 9);
        sViewsWithIds.put(R.id.activity_network_free_place_tv, 10);
        sViewsWithIds.put(R.id.activity_network_free_pay_tv, 11);
        sViewsWithIds.put(R.id.activity_network_free_deadline_tv, 12);
        sViewsWithIds.put(R.id.activity_network_free_deadline_detail_tv, 13);
        sViewsWithIds.put(R.id.activity_network_free_apply_name, 14);
        sViewsWithIds.put(R.id.activity_network_free_apply_not_correct_name_tv, 15);
        sViewsWithIds.put(R.id.activity_network_free_apply_nickname, 16);
        sViewsWithIds.put(R.id.activity_network_free_apply_not_correct_nickname_tv, 17);
        sViewsWithIds.put(R.id.activity_network_free_apply_phone, 18);
        sViewsWithIds.put(R.id.activity_network_free_apply_not_correct_phone_tv, 19);
        sViewsWithIds.put(R.id.activity_network_free_apply_btn, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityNetworkFreeApplyNameTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputName.getValue()
            //         is item.inputName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityNetworkFreeApplyNameTv);
            // localize variables for thread safety
            // item.inputName != null
            boolean itemInputNameJavaLangObjectNull = false;
            // item.inputName.getValue()
            java.lang.String itemInputNameGetValue = null;
            // item
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
            // item != null
            boolean itemJavaLangObjectNull = false;
            // item.inputName
            androidx.lifecycle.MutableLiveData<java.lang.String> itemInputName = null;



            itemJavaLangObjectNull = (item) != (null);
            if (itemJavaLangObjectNull) {


                itemInputName = item.getInputName();

                itemInputNameJavaLangObjectNull = (itemInputName) != (null);
                if (itemInputNameJavaLangObjectNull) {




                    itemInputName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityNetworkFreeApplyNicknameTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputNickName.getValue()
            //         is item.inputNickName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityNetworkFreeApplyNicknameTv);
            // localize variables for thread safety
            // item.inputNickName != null
            boolean itemInputNickNameJavaLangObjectNull = false;
            // item
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
            // item.inputNickName
            androidx.lifecycle.MutableLiveData<java.lang.String> itemInputNickName = null;
            // item != null
            boolean itemJavaLangObjectNull = false;
            // item.inputNickName.getValue()
            java.lang.String itemInputNickNameGetValue = null;



            itemJavaLangObjectNull = (item) != (null);
            if (itemJavaLangObjectNull) {


                itemInputNickName = item.getInputNickName();

                itemInputNickNameJavaLangObjectNull = (itemInputNickName) != (null);
                if (itemInputNickNameJavaLangObjectNull) {




                    itemInputNickName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityNetworkFreeApplyPhoneTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputPhone.getValue()
            //         is item.inputPhone.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityNetworkFreeApplyPhoneTv);
            // localize variables for thread safety
            // item
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
            // item.inputPhone
            androidx.lifecycle.MutableLiveData<java.lang.String> itemInputPhone = null;
            // item != null
            boolean itemJavaLangObjectNull = false;
            // item.inputPhone.getValue()
            java.lang.String itemInputPhoneGetValue = null;
            // item.inputPhone != null
            boolean itemInputPhoneJavaLangObjectNull = false;



            itemJavaLangObjectNull = (item) != (null);
            if (itemJavaLangObjectNull) {


                itemInputPhone = item.getInputPhone();

                itemInputPhoneJavaLangObjectNull = (itemInputPhone) != (null);
                if (itemInputPhoneJavaLangObjectNull) {




                    itemInputPhone.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentNetworkingFreeApplyBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private FragmentNetworkingFreeApplyBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.TextView) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (android.widget.TextView) bindings[14]
            , (android.widget.EditText) bindings[5]
            , (android.widget.TextView) bindings[16]
            , (android.widget.EditText) bindings[6]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[18]
            , (android.widget.EditText) bindings[7]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[1]
            );
        this.activityNetworkFreeApplyNameTv.setTag(null);
        this.activityNetworkFreeApplyNicknameTv.setTag(null);
        this.activityNetworkFreeApplyPhoneTv.setTag(null);
        this.activityNetworkFreeDateDetailTv.setTag(null);
        this.activityNetworkFreePayDetailTv.setTag(null);
        this.activityNetworkFreePlaceDetailTv.setTag(null);
        this.activityNetworkFreeTitleTv.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItemNetworkingInfo((androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoResponse>) object, fieldId);
            case 1 :
                return onChangeItemInputPhone((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeItemInputNickName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeItemInputName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItemNetworkingInfo(androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoResponse> ItemNetworkingInfo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItemInputPhone(androidx.lifecycle.MutableLiveData<java.lang.String> ItemInputPhone, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItemInputNickName(androidx.lifecycle.MutableLiveData<java.lang.String> ItemInputNickName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeItemInputName(androidx.lifecycle.MutableLiveData<java.lang.String> ItemInputName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String itemInputNameGetValue = null;
        java.lang.String itemNetworkingInfoResultTitle = null;
        com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoResponse itemNetworkingInfoGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
        androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoResponse> itemNetworkingInfo = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputPhone = null;
        java.lang.String integerToStringItemNetworkingInfoResultFee = null;
        java.lang.String itemInputNickNameGetValue = null;
        java.lang.String itemNetworkingInfoResultLocation = null;
        int itemNetworkingInfoResultFee = 0;
        boolean integerToStringItemNetworkingInfoResultFeeEqualsInt0 = false;
        java.lang.String itemInputPhoneGetValue = null;
        java.lang.String integerToStringItemNetworkingInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemNetworkingInfoResultFeeItemFeeFreeIntegerToStringItemNetworkingInfoResultFee = null;
        java.lang.String itemFeeFreeIntegerToStringItemNetworkingInfoResultFee = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputNickName = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputName = null;
        com.garamgaebi.garamgaebi.garamgaebi.model.NetworkingInfoReult itemNetworkingInfoResult = null;
        java.lang.String itemNetworkingInfoResultDate = null;
        java.lang.String itemConvertFeeIntegerToStringItemNetworkingInfoResultFee = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (item != null) {
                        // read item.networkingInfo
                        itemNetworkingInfo = item.getNetworkingInfo();
                    }
                    updateLiveDataRegistration(0, itemNetworkingInfo);


                    if (itemNetworkingInfo != null) {
                        // read item.networkingInfo.getValue()
                        itemNetworkingInfoGetValue = itemNetworkingInfo.getValue();
                    }


                    if (itemNetworkingInfoGetValue != null) {
                        // read item.networkingInfo.getValue().result
                        itemNetworkingInfoResult = itemNetworkingInfoGetValue.getResult();
                    }


                    if (itemNetworkingInfoResult != null) {
                        // read item.networkingInfo.getValue().result.title
                        itemNetworkingInfoResultTitle = itemNetworkingInfoResult.getTitle();
                        // read item.networkingInfo.getValue().result.location
                        itemNetworkingInfoResultLocation = itemNetworkingInfoResult.getLocation();
                        // read item.networkingInfo.getValue().result.fee
                        itemNetworkingInfoResultFee = itemNetworkingInfoResult.getFee();
                        // read item.networkingInfo.getValue().result.date
                        itemNetworkingInfoResultDate = itemNetworkingInfoResult.getDate();
                    }


                    // read Integer.toString(item.networkingInfo.getValue().result.fee)
                    integerToStringItemNetworkingInfoResultFee = java.lang.Integer.toString(itemNetworkingInfoResultFee);


                    if (integerToStringItemNetworkingInfoResultFee != null) {
                        // read Integer.toString(item.networkingInfo.getValue().result.fee).equals(0)
                        integerToStringItemNetworkingInfoResultFeeEqualsInt0 = integerToStringItemNetworkingInfoResultFee.equals(0);
                    }
                if((dirtyFlags & 0x31L) != 0) {
                    if(integerToStringItemNetworkingInfoResultFeeEqualsInt0) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (item != null) {
                        // read item.inputPhone
                        itemInputPhone = item.getInputPhone();
                    }
                    updateLiveDataRegistration(1, itemInputPhone);


                    if (itemInputPhone != null) {
                        // read item.inputPhone.getValue()
                        itemInputPhoneGetValue = itemInputPhone.getValue();
                    }
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (item != null) {
                        // read item.inputNickName
                        itemInputNickName = item.getInputNickName();
                    }
                    updateLiveDataRegistration(2, itemInputNickName);


                    if (itemInputNickName != null) {
                        // read item.inputNickName.getValue()
                        itemInputNickNameGetValue = itemInputNickName.getValue();
                    }
            }
            if ((dirtyFlags & 0x38L) != 0) {

                    if (item != null) {
                        // read item.inputName
                        itemInputName = item.getInputName();
                    }
                    updateLiveDataRegistration(3, itemInputName);


                    if (itemInputName != null) {
                        // read item.inputName.getValue()
                        itemInputNameGetValue = itemInputName.getValue();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x40L) != 0) {

                if (item != null) {
                    // read item.feeFree(Integer.toString(item.networkingInfo.getValue().result.fee))
                    itemFeeFreeIntegerToStringItemNetworkingInfoResultFee = item.feeFree(integerToStringItemNetworkingInfoResultFee);
                }
        }
        if ((dirtyFlags & 0x80L) != 0) {

                if (item != null) {
                    // read item.convertFee(Integer.toString(item.networkingInfo.getValue().result.fee))
                    itemConvertFeeIntegerToStringItemNetworkingInfoResultFee = item.convertFee(integerToStringItemNetworkingInfoResultFee);
                }
        }

        if ((dirtyFlags & 0x31L) != 0) {

                // read Integer.toString(item.networkingInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.networkingInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.networkingInfo.getValue().result.fee))
                integerToStringItemNetworkingInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemNetworkingInfoResultFeeItemFeeFreeIntegerToStringItemNetworkingInfoResultFee = ((integerToStringItemNetworkingInfoResultFeeEqualsInt0) ? (itemConvertFeeIntegerToStringItemNetworkingInfoResultFee) : (itemFeeFreeIntegerToStringItemNetworkingInfoResultFee));
        }
        // batch finished
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreeApplyNameTv, itemInputNameGetValue);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityNetworkFreeApplyNameTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityNetworkFreeApplyNameTvandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityNetworkFreeApplyNicknameTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityNetworkFreeApplyNicknameTvandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityNetworkFreeApplyPhoneTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityNetworkFreeApplyPhoneTvandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreeApplyNicknameTv, itemInputNickNameGetValue);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreeApplyPhoneTv, itemInputPhoneGetValue);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreeDateDetailTv, itemNetworkingInfoResultDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreePayDetailTv, integerToStringItemNetworkingInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemNetworkingInfoResultFeeItemFeeFreeIntegerToStringItemNetworkingInfoResultFee);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreePlaceDetailTv, itemNetworkingInfoResultLocation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityNetworkFreeTitleTv, itemNetworkingInfoResultTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item.networkingInfo
        flag 1 (0x2L): item.inputPhone
        flag 2 (0x3L): item.inputNickName
        flag 3 (0x4L): item.inputName
        flag 4 (0x5L): item
        flag 5 (0x6L): null
        flag 6 (0x7L): Integer.toString(item.networkingInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.networkingInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.networkingInfo.getValue().result.fee))
        flag 7 (0x8L): Integer.toString(item.networkingInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.networkingInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.networkingInfo.getValue().result.fee))
    flag mapping end*/
    //end
}