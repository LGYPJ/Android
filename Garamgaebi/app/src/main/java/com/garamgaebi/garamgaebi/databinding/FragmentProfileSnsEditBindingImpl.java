package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileSnsEditBindingImpl extends FragmentProfileSnsEditBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_sns_info, 4);
        sViewsWithIds.put(R.id.activity_sns_tv_nameTitle, 5);
        sViewsWithIds.put(R.id.activity_sns_tv_linkTitle, 6);
        sViewsWithIds.put(R.id.activity_sns_remove_btn, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback11;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback10;
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

    public FragmentProfileSnsEditBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private FragmentProfileSnsEditBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (androidx.appcompat.widget.AppCompatButton) bindings[7]
            , (androidx.appcompat.widget.AppCompatButton) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[5]
            );
        this.activitySnsEtLinkDesc.setTag(null);
        this.activitySnsEtName.setTag(null);
        this.activitySnsSaveBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback11 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 2);
        mCallback10 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
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
        if (BR.snsEditFragment == variableId) {
            setSnsEditFragment((com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.SnsEditFragment) variable);
        }
        else if (BR.snsViewModel == variableId) {
            setSnsViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSnsEditFragment(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.SnsEditFragment SnsEditFragment) {
        this.mSnsEditFragment = SnsEditFragment;
    }
    public void setSnsViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel SnsViewModel) {
        this.mSnsViewModel = SnsViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.snsViewModel);
        super.requestRebind();
    }
    public void setEditTextViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel EditTextViewModel) {
        this.mEditTextViewModel = EditTextViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.editTextViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeSnsViewModelSnsType((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeSnsViewModelSnsAddressIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeSnsViewModelSnsTypeIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeSnsViewModelSnsAddress((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsType(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelSnsType, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsAddressIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsAddressIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsTypeIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> SnsViewModelSnsTypeIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSnsViewModelSnsAddress(androidx.lifecycle.MutableLiveData<java.lang.String> SnsViewModelSnsAddress, int fieldId) {
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
        java.lang.Boolean snsViewModelSnsTypeIsValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsType = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsAddressIsValid = null;
        boolean snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> snsViewModelSnsTypeIsValid = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel snsViewModel = mSnsViewModel;
        android.graphics.drawable.Drawable snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout = null;
        java.lang.String snsViewModelSnsTypeGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> snsViewModelSnsAddress = null;
        java.lang.Boolean snsViewModelSnsAddressIsValidGetValue = null;
        java.lang.String snsViewModelSnsAddressGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;
        boolean snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalse = false;

        if ((dirtyFlags & 0xafL) != 0) {


            if ((dirtyFlags & 0xa1L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsType
                        snsViewModelSnsType = snsViewModel.getSnsType();
                    }
                    updateLiveDataRegistration(0, snsViewModelSnsType);


                    if (snsViewModelSnsType != null) {
                        // read snsViewModel.snsType.getValue()
                        snsViewModelSnsTypeGetValue = snsViewModelSnsType.getValue();
                    }
            }
            if ((dirtyFlags & 0xa6L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsAddressIsValid
                        snsViewModelSnsAddressIsValid = snsViewModel.getSnsAddressIsValid();
                    }
                    updateLiveDataRegistration(1, snsViewModelSnsAddressIsValid);


                    if (snsViewModelSnsAddressIsValid != null) {
                        // read snsViewModel.snsAddressIsValid.getValue()
                        snsViewModelSnsAddressIsValidGetValue = snsViewModelSnsAddressIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsAddressIsValidGetValue);
                if((dirtyFlags & 0xa6L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue) {
                            dirtyFlags |= 0x2000L;
                    }
                    else {
                            dirtyFlags |= 0x1000L;
                    }
                }
            }
            if ((dirtyFlags & 0xa8L) != 0) {

                    if (snsViewModel != null) {
                        // read snsViewModel.snsAddress
                        snsViewModelSnsAddress = snsViewModel.getSnsAddress();
                    }
                    updateLiveDataRegistration(3, snsViewModelSnsAddress);


                    if (snsViewModelSnsAddress != null) {
                        // read snsViewModel.snsAddress.getValue()
                        snsViewModelSnsAddressGetValue = snsViewModelSnsAddress.getValue();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000L) != 0) {

                if (snsViewModel != null) {
                    // read snsViewModel.snsTypeIsValid
                    snsViewModelSnsTypeIsValid = snsViewModel.getSnsTypeIsValid();
                }
                updateLiveDataRegistration(2, snsViewModelSnsTypeIsValid);


                if (snsViewModelSnsTypeIsValid != null) {
                    // read snsViewModel.snsTypeIsValid.getValue()
                    snsViewModelSnsTypeIsValidGetValue = snsViewModelSnsTypeIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(snsViewModelSnsTypeIsValidGetValue);
        }

        if ((dirtyFlags & 0xa6L) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
                snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsAddressIsValidGetValue) ? (androidxDatabindingViewDataBindingSafeUnboxSnsViewModelSnsTypeIsValidGetValue) : (false));
            if((dirtyFlags & 0xa6L) != 0) {
                if(snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalse) {
                        dirtyFlags |= 0x200L;
                        dirtyFlags |= 0x800L;
                }
                else {
                        dirtyFlags |= 0x100L;
                        dirtyFlags |= 0x400L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
                snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse = ((snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalse) ? (true) : (false));
                // read androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout = ((snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsSaveBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activitySnsSaveBtn.getContext(), R.drawable.basic_gray_btn_layout)));
        }
        // batch finished
        if ((dirtyFlags & 0xa8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsEtLinkDesc, snsViewModelSnsAddressGetValue);
        }
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activitySnsEtLinkDesc, mCallback11);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySnsEtLinkDesc, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySnsEtLinkDescandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activitySnsEtName, mCallback10);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySnsEtName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySnsEtNameandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xa1L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySnsEtName, snsViewModelSnsTypeGetValue);
        }
        if ((dirtyFlags & 0xa6L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activitySnsSaveBtn, snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseActivitySnsSaveBtnAndroidDrawableBasicBlueBtnLayoutActivitySnsSaveBtnAndroidDrawableBasicGrayBtnLayout);
            this.activitySnsSaveBtn.setClickable(snsViewModelSnsAddressIsValidSnsViewModelSnsTypeIsValidBooleanFalseBooleanTrueBooleanFalse);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnFocusLost(int sourceId , android.widget.EditText callbackArg_0, boolean callbackArg_1) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // editTextViewModel != null
                boolean editTextViewModelJavaLangObjectNull = false;
                // editTextViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



                editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
                if (editTextViewModelJavaLangObjectNull) {





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activitySnsEtLinkDesc.getResources().getString(R.string.sns_add_link_desc));
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // editTextViewModel != null
                boolean editTextViewModelJavaLangObjectNull = false;
                // editTextViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



                editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
                if (editTextViewModelJavaLangObjectNull) {





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activitySnsEtName.getResources().getString(R.string.sns_add_type_desc));
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): snsViewModel.snsType
        flag 1 (0x2L): snsViewModel.snsAddressIsValid
        flag 2 (0x3L): snsViewModel.snsTypeIsValid
        flag 3 (0x4L): snsViewModel.snsAddress
        flag 4 (0x5L): snsEditFragment
        flag 5 (0x6L): snsViewModel
        flag 6 (0x7L): editTextViewModel
        flag 7 (0x8L): null
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? true : false
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsAddressIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(snsViewModel.snsTypeIsValid.getValue()) : false
    flag mapping end*/
    //end
}