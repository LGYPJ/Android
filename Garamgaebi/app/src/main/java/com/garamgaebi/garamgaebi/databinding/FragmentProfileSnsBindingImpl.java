package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileSnsBindingImpl extends FragmentProfileSnsBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusingListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_sns_info, 7);
        sViewsWithIds.put(R.id.activity_sns_tv_nameTitle, 8);
        sViewsWithIds.put(R.id.activity_sns_tv_linkTitle, 9);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusingListener mCallback9;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusingListener mCallback8;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activitySnsEtLinkDescandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of snsViewModel.snsAddress.getValue()
            //         is snsViewModel.snsAddress.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySnsEtLinkDesc);
            // localize variables for thread safety
            // snsViewModel.snsAddress
            androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsAddress = null;
            // snsViewModel.snsAddress != null
            boolean snsViewModelSnsAddressJavaLangObjectNull = false;
            // snsViewModel.snsAddress.getValue()
            java.lang.String snsViewModelSnsAddressGetValue = null;
            // snsViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
            // snsViewModel != null
            boolean snsViewModelJavaLangObjectNull = false;



            snsViewModelJavaLangObjectNull = (snsViewModel) != (null);
            if (snsViewModelJavaLangObjectNull) {


                snsViewModelSnsAddress = snsViewModel.getSnsAddress();

                snsViewModelSnsAddressJavaLangObjectNull = (snsViewModelSnsAddress) != (null);
                if (snsViewModelSnsAddressJavaLangObjectNull) {




                    snsViewModelSnsAddress.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activitySnsEtNameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of snsViewModel.snsType.getValue()
            //         is snsViewModel.snsType.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySnsEtName);
            // localize variables for thread safety
            // snsViewModel.snsType
            androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsType = null;
            // snsViewModel.snsType.getValue()
            java.lang.String snsViewModelSnsTypeGetValue = null;
            // snsViewModel.snsType != null
            boolean snsViewModelSnsTypeJavaLangObjectNull = false;
            // snsViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
            // snsViewModel != null
            boolean snsViewModelJavaLangObjectNull = false;



            snsViewModelJavaLangObjectNull = (snsViewModel) != (null);
            if (snsViewModelJavaLangObjectNull) {


                snsViewModelSnsType = snsViewModel.getSnsType();

                snsViewModelSnsTypeJavaLangObjectNull = (snsViewModelSnsType) != (null);
                if (snsViewModelSnsTypeJavaLangObjectNull) {




                    snsViewModelSnsType.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentProfileSnsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentProfileSnsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 12
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (androidx.appcompat.widget.AppCompatButton) bindings[6]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[8]
            );
        this.activitySnsEtLinkDesc.setTag(null);
        this.activitySnsEtName.setTag(null);
        this.activitySnsEtNameLength.setTag(null);
        this.activitySnsSaveBtn.setTag(null);
        this.activitySnsTvLinkState.setTag(null);
        this.activitySnsTvNameState.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback9 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusingListener(this, 2);
        mCallback8 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusingListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8000L;
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
        if (BR.common == variableId) {
            setCommon((com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction) variable);
        }
        else if (BR.snsViewModel == variableId) {
            setSnsViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel) variable);
        }
        else if (BR.snsAddFragment == variableId) {
            setSnsAddFragment((com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.SnsAddFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCommon(@Nullable com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction Common) {
        this.mCommon = Common;
    }
    public void setSnsViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel SnsViewModel) {
        this.mSnsViewModel = SnsViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2000L;
        }
        notifyPropertyChanged(BR.snsViewModel);
        super.requestRebind();
    }
    public void setSnsAddFragment(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.SnsAddFragment SnsAddFragment) {
        this.mSnsAddFragment = SnsAddFragment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeSnsViewModelTypeState((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeSnsViewModelSnsType((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeSnsViewModelTypeFirst((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeSnsViewModelSnsTypeIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeSnsViewModelSnsAddress((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeSnsViewModelSnsTypeFocusing((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 6 :
                return onChangeSnsViewModelTypeInputDesc((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeSnsViewModelLinkState((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeSnsViewModelSnsAddressIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 9 :
                return onChangeSnsViewModelSnsAddressFocusing((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 10 :
                return onChangeSnsViewModelAddressInputDesc((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 11 :
                return onChangeSnsViewModelAddressFirst((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeSnsViewModelTypeState(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelTypeState, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsType(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelSnsType, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelTypeFirst(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelTypeFirst, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsTypeIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsTypeIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsAddress(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelSnsAddress, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsTypeFocusing(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsTypeFocusing, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelTypeInputDesc(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelTypeInputDesc, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelLinkState(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelLinkState, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsAddressIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsAddressIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsAddressFocusing(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsAddressFocusing, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelAddressInputDesc(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelAddressInputDesc, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelAddressFirst(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelAddressFirst, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x800L;
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
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelTypeState = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsType = null;
        int snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidViewINVISIBLEViewVISIBLE = 0;
        boolean snsViewModelAddressFirst = false;
        java.lang.String snsViewModelSnsTypeFocusingJavaLangStringSnsViewModelTypeInputDesc = null;
        android.graphics.drawable.Drawable snsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayout = null;
        java.lang.Boolean snsViewModelSnsAddressFocusingGetValue = null;
        java.lang.Boolean snsViewModelAddressFirstGetValue = null;
        java.lang.String snsViewModelSnsTypeLengthJavaLangString22 = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelTypeFirst = null;
        android.graphics.drawable.Drawable snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidSnsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayoutActivitySnsEtNameAndroidDrawableBasicRedBorderLayout = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsTypeIsValid = null;
        boolean snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid = false;
        java.lang.String snsViewModelTypeStateGetValue = null;
        boolean snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse = false;
        boolean snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalse = false;
        int snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidViewINVISIBLEViewVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsAddress = null;
        boolean SnsViewModelTypeFirst1 = false;
        boolean snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsTypeFocusing = null;
        android.graphics.drawable.Drawable snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidSnsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicRedBorderLayout = null;
        java.lang.String snsViewModelLinkStateGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelTypeInputDesc = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelLinkState = null;
        java.lang.Boolean snsViewModelTypeFirstGetValue = null;
        java.lang.Boolean snsViewModelSnsTypeIsValidGetValue = null;
        android.graphics.drawable.Drawable snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsAddressIsValid = null;
        android.graphics.drawable.Drawable snsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayout = null;
        boolean snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalse = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue = false;
        int snsViewModelSnsTypeLength = 0;
        boolean snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsAddressFocusing = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
        java.lang.String snsViewModelTypeInputDescGetValue = null;
        java.lang.String snsViewModelAddressInputDescGetValue = null;
        java.lang.String snsViewModelSnsTypeGetValue = null;
        java.lang.Boolean snsViewModelSnsTypeFocusingGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelAddressInputDesc = null;
        java.lang.String snsViewModelSnsAddressFocusingJavaLangStringSnsViewModelAddressInputDesc = null;
        java.lang.Boolean snsViewModelSnsAddressIsValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelAddressFirst1 = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelTypeFirstGetValue = false;
        java.lang.String snsViewModelSnsAddressGetValue = null;

        if ((dirtyFlags & 0xafffL) != 0) {


            if ((dirtyFlags & 0xa001L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.typeState
                        snsViewModelTypeState = snsViewModel.getTypeState();
                    }
                    updateLiveDataRegistration(0, snsViewModelTypeState);


                    if (snsViewModelTypeState != null) {
                        // read snsViewModel.typeState.getValue()
                        snsViewModelTypeStateGetValue = snsViewModelTypeState.getValue();
                    }
            }
            if ((dirtyFlags & 0xa002L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsType
                        snsViewModelSnsType = snsViewModel.getSnsType();
                    }
                    updateLiveDataRegistration(1, snsViewModelSnsType);


                    if (snsViewModelSnsType != null) {
                        // read snsViewModel.snsType.getValue()
                        snsViewModelSnsTypeGetValue = snsViewModelSnsType.getValue();
                    }


                    if (snsViewModelSnsTypeGetValue != null) {
                        // read snsViewModel.snsType.getValue().length()
                        snsViewModelSnsTypeLength = snsViewModelSnsTypeGetValue.length();
                    }


                    // read (snsViewModel.snsType.getValue().length()) + ("/22")
                    snsViewModelSnsTypeLengthJavaLangString22 = (snsViewModelSnsTypeLength) + ("/22");
            }
            if ((dirtyFlags & 0xa92cL) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.typeFirst
                        snsViewModelTypeFirst = snsViewModel.getTypeFirst();
                    }
                    updateLiveDataRegistration(2, snsViewModelTypeFirst);


                    if (snsViewModelTypeFirst != null) {
                        // read snsViewModel.typeFirst.getValue()
                        snsViewModelTypeFirstGetValue = snsViewModelTypeFirst.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxSnsViewModelTypeFirstGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelTypeFirstGetValue);
                if((dirtyFlags & 0xa02cL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelTypeFirstGetValue) {
                            dirtyFlags |= 0x2000000L;
                    }
                    else {
                            dirtyFlags |= 0x1000000L;
                    }
                }

                if ((dirtyFlags & 0xa90cL) != 0) {

                        // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue())
                        SnsViewModelTypeFirst1 = !androidxDatabindingViewDataBindingSafeUnboxSnsViewModelTypeFirstGetValue;
                    if((dirtyFlags & 0xa90cL) != 0) {
                        if(SnsViewModelTypeFirst1) {
                                dirtyFlags |= 0x20000000L;
                        }
                        else {
                                dirtyFlags |= 0x10000000L;
                        }
                    }
                }
            }
            if ((dirtyFlags & 0xa010L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsAddress
                        snsViewModelSnsAddress = snsViewModel.getSnsAddress();
                    }
                    updateLiveDataRegistration(4, snsViewModelSnsAddress);


                    if (snsViewModelSnsAddress != null) {
                        // read snsViewModel.snsAddress.getValue()
                        snsViewModelSnsAddressGetValue = snsViewModelSnsAddress.getValue();
                    }
            }
            if ((dirtyFlags & 0xa060L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsTypeFocusing
                        snsViewModelSnsTypeFocusing = snsViewModel.getSnsTypeFocusing();
                    }
                    updateLiveDataRegistration(5, snsViewModelSnsTypeFocusing);


                    if (snsViewModelSnsTypeFocusing != null) {
                        // read snsViewModel.snsTypeFocusing.getValue()
                        snsViewModelSnsTypeFocusingGetValue = snsViewModelSnsTypeFocusing.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsTypeFocusingGetValue);
                if((dirtyFlags & 0xa060L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) {
                            dirtyFlags |= 0x80000L;
                    }
                    else {
                            dirtyFlags |= 0x40000L;
                    }
                }
                if((dirtyFlags & 0x800000L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) {
                            dirtyFlags |= 0x200000L;
                    }
                    else {
                            dirtyFlags |= 0x100000L;
                    }
                }
            }
            if ((dirtyFlags & 0xa080L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.linkState
                        snsViewModelLinkState = snsViewModel.getLinkState();
                    }
                    updateLiveDataRegistration(7, snsViewModelLinkState);


                    if (snsViewModelLinkState != null) {
                        // read snsViewModel.linkState.getValue()
                        snsViewModelLinkStateGetValue = snsViewModelLinkState.getValue();
                    }
            }
            if ((dirtyFlags & 0xa600L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsAddressFocusing
                        snsViewModelSnsAddressFocusing = snsViewModel.getSnsAddressFocusing();
                    }
                    updateLiveDataRegistration(9, snsViewModelSnsAddressFocusing);


                    if (snsViewModelSnsAddressFocusing != null) {
                        // read snsViewModel.snsAddressFocusing.getValue()
                        snsViewModelSnsAddressFocusingGetValue = snsViewModelSnsAddressFocusing.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsAddressFocusingGetValue);
                if((dirtyFlags & 0x800000000L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) {
                            dirtyFlags |= 0x8000000000L;
                    }
                    else {
                            dirtyFlags |= 0x4000000000L;
                    }
                }
                if((dirtyFlags & 0xa600L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) {
                            dirtyFlags |= 0x200000000000L;
                    }
                    else {
                            dirtyFlags |= 0x100000000000L;
                    }
                }
            }
            if ((dirtyFlags & 0xab00L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.addressFirst
                        SnsViewModelAddressFirst1 = snsViewModel.getAddressFirst();
                    }
                    updateLiveDataRegistration(11, SnsViewModelAddressFirst1);


                    if (SnsViewModelAddressFirst1 != null) {
                        // read snsViewModel.addressFirst.getValue()
                        snsViewModelAddressFirstGetValue = SnsViewModelAddressFirst1.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelAddressFirstGetValue);
                if((dirtyFlags & 0xab00L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue) {
                            dirtyFlags |= 0x200000000L;
                    }
                    else {
                            dirtyFlags |= 0x100000000L;
                    }
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x1000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsTypeIsValid
                    snsViewModelSnsTypeIsValid = snsViewModel.getSnsTypeIsValid();
                }
                updateLiveDataRegistration(3, snsViewModelSnsTypeIsValid);


                if (snsViewModelSnsTypeIsValid != null) {
                    // read snsViewModel.snsTypeIsValid.getValue()
                    snsViewModelSnsTypeIsValidGetValue = snsViewModelSnsTypeIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsTypeIsValidGetValue);
        }
        if ((dirtyFlags & 0x20000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.addressFirst
                    SnsViewModelAddressFirst1 = snsViewModel.getAddressFirst();
                }
                updateLiveDataRegistration(11, SnsViewModelAddressFirst1);


                if (SnsViewModelAddressFirst1 != null) {
                    // read snsViewModel.addressFirst.getValue()
                    snsViewModelAddressFirstGetValue = SnsViewModelAddressFirst1.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelAddressFirstGetValue);
            if((dirtyFlags & 0xab00L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue) {
                        dirtyFlags |= 0x200000000L;
                }
                else {
                        dirtyFlags |= 0x100000000L;
                }
            }


                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue())
                snsViewModelAddressFirst = !androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue;
        }

        if ((dirtyFlags & 0xa02cL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
                snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelTypeFirstGetValue) ? (true) : (androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue));
            if((dirtyFlags & 0xa00cL) != 0) {
                if(snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid) {
                        dirtyFlags |= 0x20000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                }
            }
            if((dirtyFlags & 0xa02cL) != 0) {
                if(snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid) {
                        dirtyFlags |= 0x800000L;
                }
                else {
                        dirtyFlags |= 0x400000L;
                }
            }

            if ((dirtyFlags & 0xa00cL) != 0) {

                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
                    snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidViewINVISIBLEViewVISIBLE = ((snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
            }
        }
        if ((dirtyFlags & 0xa90cL) != 0) {

                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false
                snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalse = ((SnsViewModelTypeFirst1) ? (snsViewModelAddressFirst) : (false));
            if((dirtyFlags & 0xa90cL) != 0) {
                if(snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalse) {
                        dirtyFlags |= 0x20000000000L;
                }
                else {
                        dirtyFlags |= 0x10000000000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x800000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsTypeFocusing
                    snsViewModelSnsTypeFocusing = snsViewModel.getSnsTypeFocusing();
                }
                updateLiveDataRegistration(5, snsViewModelSnsTypeFocusing);


                if (snsViewModelSnsTypeFocusing != null) {
                    // read snsViewModel.snsTypeFocusing.getValue()
                    snsViewModelSnsTypeFocusingGetValue = snsViewModelSnsTypeFocusing.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsTypeFocusingGetValue);
            if((dirtyFlags & 0xa060L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) {
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x40000L;
                }
            }
            if((dirtyFlags & 0x800000L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) {
                        dirtyFlags |= 0x200000L;
                }
                else {
                        dirtyFlags |= 0x100000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
                snsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayout = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtName.getContext(), R.drawable.basic_black_border_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtName.getContext(), R.drawable.basic_gray_border_layout)));
        }
        if ((dirtyFlags & 0x20100000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsAddressIsValid
                    snsViewModelSnsAddressIsValid = snsViewModel.getSnsAddressIsValid();
                }
                updateLiveDataRegistration(8, snsViewModelSnsAddressIsValid);


                if (snsViewModelSnsAddressIsValid != null) {
                    // read snsViewModel.snsAddressIsValid.getValue()
                    snsViewModelSnsAddressIsValidGetValue = snsViewModelSnsAddressIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsAddressIsValidGetValue);
        }

        if ((dirtyFlags & 0xa02cL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
                snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidSnsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayoutActivitySnsEtNameAndroidDrawableBasicRedBorderLayout = ((snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValid) ? (snsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayout) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtName.getContext(), R.drawable.basic_red_border_layout)));
        }
        if ((dirtyFlags & 0xab00L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue())
                snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelAddressFirstGetValue) ? (true) : (androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue));
            if((dirtyFlags & 0xa900L) != 0) {
                if(snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid) {
                        dirtyFlags |= 0x80000000L;
                }
                else {
                        dirtyFlags |= 0x40000000L;
                }
            }
            if((dirtyFlags & 0xab00L) != 0) {
                if(snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid) {
                        dirtyFlags |= 0x800000000L;
                }
                else {
                        dirtyFlags |= 0x400000000L;
                }
            }

            if ((dirtyFlags & 0xa900L) != 0) {

                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
                    snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidViewINVISIBLEViewVISIBLE = ((snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
            }
        }
        if ((dirtyFlags & 0xa90cL) != 0) {

                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false
                snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalse = ((snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue) : (false));
            if((dirtyFlags & 0xa90cL) != 0) {
                if(snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalse) {
                        dirtyFlags |= 0x80000000000L;
                }
                else {
                        dirtyFlags |= 0x40000000000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x80000000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsTypeIsValid
                    snsViewModelSnsTypeIsValid = snsViewModel.getSnsTypeIsValid();
                }
                updateLiveDataRegistration(3, snsViewModelSnsTypeIsValid);


                if (snsViewModelSnsTypeIsValid != null) {
                    // read snsViewModel.snsTypeIsValid.getValue()
                    snsViewModelSnsTypeIsValidGetValue = snsViewModelSnsTypeIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsTypeIsValidGetValue);
        }
        if ((dirtyFlags & 0x40000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.typeInputDesc
                    snsViewModelTypeInputDesc = snsViewModel.getTypeInputDesc();
                }
                updateLiveDataRegistration(6, snsViewModelTypeInputDesc);


                if (snsViewModelTypeInputDesc != null) {
                    // read snsViewModel.typeInputDesc.getValue()
                    snsViewModelTypeInputDescGetValue = snsViewModelTypeInputDesc.getValue();
                }
        }
        if ((dirtyFlags & 0x800000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsAddressFocusing
                    snsViewModelSnsAddressFocusing = snsViewModel.getSnsAddressFocusing();
                }
                updateLiveDataRegistration(9, snsViewModelSnsAddressFocusing);


                if (snsViewModelSnsAddressFocusing != null) {
                    // read snsViewModel.snsAddressFocusing.getValue()
                    snsViewModelSnsAddressFocusingGetValue = snsViewModelSnsAddressFocusing.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsAddressFocusingGetValue);
            if((dirtyFlags & 0x800000000L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) {
                        dirtyFlags |= 0x8000000000L;
                }
                else {
                        dirtyFlags |= 0x4000000000L;
                }
            }
            if((dirtyFlags & 0xa600L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) {
                        dirtyFlags |= 0x200000000000L;
                }
                else {
                        dirtyFlags |= 0x100000000000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
                snsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayout = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtLinkDesc.getContext(), R.drawable.basic_black_border_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtLinkDesc.getContext(), R.drawable.basic_gray_border_layout)));
        }

        if ((dirtyFlags & 0xa060L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? "" : snsViewModel.typeInputDesc.getValue()
                snsViewModelSnsTypeFocusingJavaLangStringSnsViewModelTypeInputDesc = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeFocusingGetValue) ? ("") : (snsViewModelTypeInputDescGetValue));
        }
        if ((dirtyFlags & 0xab00L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
                snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidSnsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicRedBorderLayout = ((snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValid) ? (snsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayout) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsEtLinkDesc.getContext(), R.drawable.basic_red_border_layout)));
        }
        if ((dirtyFlags & 0xa90cL) != 0) {

                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
                snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalse = ((snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue) : (false));
            if((dirtyFlags & 0xa90cL) != 0) {
                if(snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalse) {
                        dirtyFlags |= 0x8000000L;
                        dirtyFlags |= 0x2000000000L;
                }
                else {
                        dirtyFlags |= 0x4000000L;
                        dirtyFlags |= 0x1000000000L;
                }
            }


                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
                snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse = ((snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalse) ? (true) : (false));
                // read !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout = ((snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsSaveBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsSaveBtn.getContext(), R.drawable.basic_gray_btn_layout)));
        }
        // batch finished

        if ((dirtyFlags & 0x100000000000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.addressInputDesc
                    snsViewModelAddressInputDesc = snsViewModel.getAddressInputDesc();
                }
                updateLiveDataRegistration(10, snsViewModelAddressInputDesc);


                if (snsViewModelAddressInputDesc != null) {
                    // read snsViewModel.addressInputDesc.getValue()
                    snsViewModelAddressInputDescGetValue = snsViewModelAddressInputDesc.getValue();
                }
        }

        if ((dirtyFlags & 0xa600L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? "" : snsViewModel.addressInputDesc.getValue()
                snsViewModelSnsAddressFocusingJavaLangStringSnsViewModelAddressInputDesc = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressFocusingGetValue) ? ("") : (snsViewModelAddressInputDescGetValue));
        }
        // batch finished
        if ((dirtyFlags & 0xab00L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activitySnsEtLinkDesc, snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidSnsViewModelSnsAddressFocusingActivitySnsEtLinkDescAndroidDrawableBasicBlackBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicGrayBorderLayoutActivitySnsEtLinkDescAndroidDrawableBasicRedBorderLayout);
        }
        if ((dirtyFlags & 0xa600L) != 0) {
            // api target 1

            this.activitySnsEtLinkDesc.setHint(snsViewModelSnsAddressFocusingJavaLangStringSnsViewModelAddressInputDesc);
        }
        if ((dirtyFlags & 0xa010L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsEtLinkDesc, snsViewModelSnsAddressGetValue);
        }
        if ((dirtyFlags & 0x8000L) != 0) {
            // api target 1

            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusing(this.activitySnsEtLinkDesc, mCallback9);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySnsEtLinkDesc, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySnsEtLinkDescandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusing(this.activitySnsEtName, mCallback8);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySnsEtName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySnsEtNameandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xa02cL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activitySnsEtName, snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidSnsViewModelSnsTypeFocusingActivitySnsEtNameAndroidDrawableBasicBlackBorderLayoutActivitySnsEtNameAndroidDrawableBasicGrayBorderLayoutActivitySnsEtNameAndroidDrawableBasicRedBorderLayout);
        }
        if ((dirtyFlags & 0xa060L) != 0) {
            // api target 1

            this.activitySnsEtName.setHint(snsViewModelSnsTypeFocusingJavaLangStringSnsViewModelTypeInputDesc);
        }
        if ((dirtyFlags & 0xa002L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsEtName, snsViewModelSnsTypeGetValue);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsEtNameLength, snsViewModelSnsTypeLengthJavaLangString22);
        }
        if ((dirtyFlags & 0xa90cL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activitySnsSaveBtn, snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout);
            this.activitySnsSaveBtn.setClickable(snsViewModelTypeFirstSnsViewModelAddressFirstBooleanFalseSnsViewModelSnsAddressIsValidBooleanFalseSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse);
        }
        if ((dirtyFlags & 0xa080L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsTvLinkState, snsViewModelLinkStateGetValue);
        }
        if ((dirtyFlags & 0xa900L) != 0) {
            // api target 1

            this.activitySnsTvLinkState.setVisibility(snsViewModelAddressFirstBooleanTrueSnsViewModelSnsAddressIsValidViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 0xa001L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsTvNameState, snsViewModelTypeStateGetValue);
        }
        if ((dirtyFlags & 0xa00cL) != 0) {
            // api target 1

            this.activitySnsTvNameState.setVisibility(snsViewModelTypeFirstBooleanTrueSnsViewModelSnsTypeIsValidViewINVISIBLEViewVISIBLE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnFocusing(int sourceId , boolean callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // snsViewModel.addressFirst
                androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelAddressFirst = null;
                // snsViewModel.snsAddressFocusing
                androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsAddressFocusing = null;
                // snsViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
                // snsViewModel != null
                boolean snsViewModelJavaLangObjectNull = false;



                snsViewModelJavaLangObjectNull = (snsViewModel) != (null);
                if (snsViewModelJavaLangObjectNull) {




                    snsViewModelSnsAddressFocusing = snsViewModel.getSnsAddressFocusing();



                    snsViewModelAddressFirst = snsViewModel.getAddressFirst();


                    snsViewModel.setBoolean(snsViewModelSnsAddressFocusing, snsViewModelAddressFirst, callbackArg_0);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // snsViewModel.snsTypeFocusing
                androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsTypeFocusing = null;
                // snsViewModel.typeFirst
                androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelTypeFirst = null;
                // snsViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
                // snsViewModel != null
                boolean snsViewModelJavaLangObjectNull = false;



                snsViewModelJavaLangObjectNull = (snsViewModel) != (null);
                if (snsViewModelJavaLangObjectNull) {




                    snsViewModelSnsTypeFocusing = snsViewModel.getSnsTypeFocusing();



                    snsViewModelTypeFirst = snsViewModel.getTypeFirst();


                    snsViewModel.setBoolean(snsViewModelSnsTypeFocusing, snsViewModelTypeFirst, callbackArg_0);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): snsViewModel.typeState
        flag 1 (0x2L): snsViewModel.snsType
        flag 2 (0x3L): snsViewModel.typeFirst
        flag 3 (0x4L): snsViewModel.snsTypeIsValid
        flag 4 (0x5L): snsViewModel.snsAddress
        flag 5 (0x6L): snsViewModel.snsTypeFocusing
        flag 6 (0x7L): snsViewModel.typeInputDesc
        flag 7 (0x8L): snsViewModel.linkState
        flag 8 (0x9L): snsViewModel.snsAddressIsValid
        flag 9 (0xaL): snsViewModel.snsAddressFocusing
        flag 10 (0xbL): snsViewModel.addressInputDesc
        flag 11 (0xcL): snsViewModel.addressFirst
        flag 12 (0xdL): common
        flag 13 (0xeL): snsViewModel
        flag 14 (0xfL): snsAddFragment
        flag 15 (0x10L): null
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? "" : snsViewModel.typeInputDesc.getValue()
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? "" : snsViewModel.typeInputDesc.getValue()
        flag 20 (0x15L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 21 (0x16L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 22 (0x17L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
        flag 23 (0x18L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
        flag 24 (0x19L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
        flag 25 (0x1aL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
        flag 26 (0x1bL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
        flag 27 (0x1cL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
        flag 28 (0x1dL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false
        flag 29 (0x1eL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false
        flag 30 (0x1fL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 31 (0x20L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 32 (0x21L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue())
        flag 33 (0x22L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue())
        flag 34 (0x23L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
        flag 35 (0x24L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) ? true : androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout : @android:drawable/basic_red_border_layout
        flag 36 (0x25L): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 37 (0x26L): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 38 (0x27L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 39 (0x28L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 40 (0x29L): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false
        flag 41 (0x2aL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false
        flag 42 (0x2bL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
        flag 43 (0x2cL): !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.typeFirst.getValue()) ? !androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.addressFirst.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
        flag 44 (0x2dL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? "" : snsViewModel.addressInputDesc.getValue()
        flag 45 (0x2eL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressFocusing.getValue()) ? "" : snsViewModel.addressInputDesc.getValue()
    flag mapping end*/
    //end
}