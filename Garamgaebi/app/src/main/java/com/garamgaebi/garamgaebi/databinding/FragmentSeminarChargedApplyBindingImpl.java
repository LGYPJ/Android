package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSeminarChargedApplyBindingImpl extends FragmentSeminarChargedApplyBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_seminar_charged_scroll, 9);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_info, 10);
        sViewsWithIds.put(R.id.activity_seminar_charged_date_tv, 11);
        sViewsWithIds.put(R.id.activity_seminar_charged_place_tv, 12);
        sViewsWithIds.put(R.id.activity_seminar_charged_pay_tv, 13);
        sViewsWithIds.put(R.id.activity_seminar_charged_deadline_tv, 14);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_name, 15);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_not_correct_name_tv, 16);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_nickname, 17);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_not_correct_nickname_tv, 18);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_phone, 19);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_not_correct_phone_tv, 20);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_content_box, 21);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv1, 22);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_account, 23);
        sViewsWithIds.put(R.id.activity_seminar_charged_copy_btn, 24);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv2, 25);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv3, 26);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv4, 27);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv5, 28);
        sViewsWithIds.put(R.id.activity_seminar_charged_info_tv6, 29);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_cancel, 30);
        sViewsWithIds.put(R.id.activity_seminar_charged_apply_btn, 31);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activitySeminarChargedApplyNameTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputName.getValue()
            //         is item.inputName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySeminarChargedApplyNameTv);
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
    private androidx.databinding.InverseBindingListener activitySeminarChargedApplyNicknameTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputNickName.getValue()
            //         is item.inputNickName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySeminarChargedApplyNicknameTv);
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
    private androidx.databinding.InverseBindingListener activitySeminarChargedApplyPhoneTvandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputPhone.getValue()
            //         is item.inputPhone.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySeminarChargedApplyPhoneTv);
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

    public FragmentSeminarChargedApplyBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds));
    }
    private FragmentSeminarChargedApplyBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.TextView) bindings[31]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[30]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[21]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[10]
            , (android.widget.TextView) bindings[15]
            , (android.widget.EditText) bindings[6]
            , (android.widget.TextView) bindings[17]
            , (android.widget.EditText) bindings[7]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[19]
            , (android.widget.EditText) bindings[8]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[24]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[12]
            , (android.widget.ScrollView) bindings[9]
            , (android.widget.TextView) bindings[1]
            );
        this.activitySeminarChargedApplyNameTv.setTag(null);
        this.activitySeminarChargedApplyNicknameTv.setTag(null);
        this.activitySeminarChargedApplyPhoneTv.setTag(null);
        this.activitySeminarChargedDateDetailTv.setTag(null);
        this.activitySeminarChargedDeadlineDetailTv.setTag(null);
        this.activitySeminarChargedPayDetailTv.setTag(null);
        this.activitySeminarChargedPlaceDetailTv.setTag(null);
        this.activitySeminarChargedTitleTv.setTag(null);
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
                return onChangeItemInputName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeItemInputPhone((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeItemInputNickName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeItemSeminarInfo((androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItemInputName(androidx.lifecycle.MutableLiveData<java.lang.String> ItemInputName, int fieldId) {
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
    private boolean onChangeItemSeminarInfo(androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse> ItemSeminarInfo, int fieldId) {
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
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
        com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse itemSeminarInfoGetValue = null;
        int itemSeminarInfoResultFee = 0;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputName = null;
        java.lang.String integerToStringItemSeminarInfoResultFee = null;
        java.lang.String itemSeminarInfoResultTitle = null;
        java.lang.String itemSeminarInfoResultLocation = null;
        boolean integerToStringItemSeminarInfoResultFeeEqualsInt0 = false;
        java.lang.String integerToStringItemSeminarInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemSeminarInfoResultFeeItemFeeFreeIntegerToStringItemSeminarInfoResultFee = null;
        java.lang.String itemFeeFreeIntegerToStringItemSeminarInfoResultFee = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputPhone = null;
        java.lang.String itemInputNickNameGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar.data.SeminarDetailInfoResult itemSeminarInfoResult = null;
        java.lang.String itemInputPhoneGetValue = null;
        java.lang.String itemConvertFeeIntegerToStringItemSeminarInfoResultFee = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputNickName = null;
        androidx.lifecycle.LiveData<com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar.data.SeminarDetailInfoResponse> itemSeminarInfo = null;
        java.lang.String itemSeminarInfoResultDate = null;
        java.lang.String itemSeminarInfoResultEndDate = null;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (item != null) {
                        // read item.inputName
                        itemInputName = item.getInputName();
                    }
                    updateLiveDataRegistration(0, itemInputName);


                    if (itemInputName != null) {
                        // read item.inputName.getValue()
                        itemInputNameGetValue = itemInputName.getValue();
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
                        // read item.seminarInfo
                        itemSeminarInfo = item.getSeminarInfo();
                    }
                    updateLiveDataRegistration(3, itemSeminarInfo);


                    if (itemSeminarInfo != null) {
                        // read item.seminarInfo.getValue()
                        itemSeminarInfoGetValue = itemSeminarInfo.getValue();
                    }


                    if (itemSeminarInfoGetValue != null) {
                        // read item.seminarInfo.getValue().result
                        itemSeminarInfoResult = itemSeminarInfoGetValue.getResult();
                    }


                    if (itemSeminarInfoResult != null) {
                        // read item.seminarInfo.getValue().result.fee
                        itemSeminarInfoResultFee = itemSeminarInfoResult.getFee();
                        // read item.seminarInfo.getValue().result.title
                        itemSeminarInfoResultTitle = itemSeminarInfoResult.getTitle();
                        // read item.seminarInfo.getValue().result.location
                        itemSeminarInfoResultLocation = itemSeminarInfoResult.getLocation();
                        // read item.seminarInfo.getValue().result.date
                        itemSeminarInfoResultDate = itemSeminarInfoResult.getDate();
                        // read item.seminarInfo.getValue().result.endDate
                        itemSeminarInfoResultEndDate = itemSeminarInfoResult.getEndDate();
                    }


                    // read Integer.toString(item.seminarInfo.getValue().result.fee)
                    integerToStringItemSeminarInfoResultFee = java.lang.Integer.toString(itemSeminarInfoResultFee);


                    if (integerToStringItemSeminarInfoResultFee != null) {
                        // read Integer.toString(item.seminarInfo.getValue().result.fee).equals(0)
                        integerToStringItemSeminarInfoResultFeeEqualsInt0 = integerToStringItemSeminarInfoResultFee.equals(0);
                    }
                if((dirtyFlags & 0x38L) != 0) {
                    if(integerToStringItemSeminarInfoResultFeeEqualsInt0) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x40L) != 0) {

                if (item != null) {
                    // read item.feeFree(Integer.toString(item.seminarInfo.getValue().result.fee))
                    itemFeeFreeIntegerToStringItemSeminarInfoResultFee = item.feeFree(integerToStringItemSeminarInfoResultFee);
                }
        }
        if ((dirtyFlags & 0x80L) != 0) {

                if (item != null) {
                    // read item.convertFee(Integer.toString(item.seminarInfo.getValue().result.fee))
                    itemConvertFeeIntegerToStringItemSeminarInfoResultFee = item.convertFee(integerToStringItemSeminarInfoResultFee);
                }
        }

        if ((dirtyFlags & 0x38L) != 0) {

                // read Integer.toString(item.seminarInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.seminarInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.seminarInfo.getValue().result.fee))
                integerToStringItemSeminarInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemSeminarInfoResultFeeItemFeeFreeIntegerToStringItemSeminarInfoResultFee = ((integerToStringItemSeminarInfoResultFeeEqualsInt0) ? (itemConvertFeeIntegerToStringItemSeminarInfoResultFee) : (itemFeeFreeIntegerToStringItemSeminarInfoResultFee));
        }
        // batch finished
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedApplyNameTv, itemInputNameGetValue);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySeminarChargedApplyNameTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySeminarChargedApplyNameTvandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySeminarChargedApplyNicknameTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySeminarChargedApplyNicknameTvandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySeminarChargedApplyPhoneTv, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySeminarChargedApplyPhoneTvandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedApplyNicknameTv, itemInputNickNameGetValue);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedApplyPhoneTv, itemInputPhoneGetValue);
        }
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedDateDetailTv, itemSeminarInfoResultDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedDeadlineDetailTv, itemSeminarInfoResultEndDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedPayDetailTv, integerToStringItemSeminarInfoResultFeeEqualsInt0ItemConvertFeeIntegerToStringItemSeminarInfoResultFeeItemFeeFreeIntegerToStringItemSeminarInfoResultFee);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedPlaceDetailTv, itemSeminarInfoResultLocation);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySeminarChargedTitleTv, itemSeminarInfoResultTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item.inputName
        flag 1 (0x2L): item.inputPhone
        flag 2 (0x3L): item.inputNickName
        flag 3 (0x4L): item.seminarInfo
        flag 4 (0x5L): item
        flag 5 (0x6L): null
        flag 6 (0x7L): Integer.toString(item.seminarInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.seminarInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.seminarInfo.getValue().result.fee))
        flag 7 (0x8L): Integer.toString(item.seminarInfo.getValue().result.fee).equals(0) ? item.convertFee(Integer.toString(item.seminarInfo.getValue().result.fee)) : item.feeFree(Integer.toString(item.seminarInfo.getValue().result.fee))
    flag mapping end*/
    //end
}