package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentServicecenterBindingImpl extends FragmentServicecenterBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_servicecenter_info, 6);
        sViewsWithIds.put(R.id.activity_servicecenter_tv_desc, 7);
        sViewsWithIds.put(R.id.activity_servicecenter_iv_option, 8);
        sViewsWithIds.put(R.id.activity_servicecenter_checkbox_rl, 9);
        sViewsWithIds.put(R.id.activity_servicecenter_checkbox_desc, 10);
        sViewsWithIds.put(R.id.activity_servicecenter_tv_logout, 11);
        sViewsWithIds.put(R.id.activity_servicecenter_tv_between, 12);
        sViewsWithIds.put(R.id.activity_servicecenter_tv_withdrawal, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback4;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityServicecenterCheckboxandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of serviceCenterViewModel.agree.getValue()
            //         is serviceCenterViewModel.agree.setValue((java.lang.Boolean) callbackArg_0)
            boolean callbackArg_0 = activityServicecenterCheckbox.isChecked();
            // localize variables for thread safety
            // serviceCenterViewModel.agree != null
            boolean serviceCenterViewModelAgreeJavaLangObjectNull = false;
            // serviceCenterViewModel != null
            boolean serviceCenterViewModelJavaLangObjectNull = false;
            // serviceCenterViewModel.agree.getValue()
            java.lang.Boolean serviceCenterViewModelAgreeGetValue = null;
            // serviceCenterViewModel.agree
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> serviceCenterViewModelAgree = null;
            // serviceCenterViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel serviceCenterViewModel = mServiceCenterViewModel;



            serviceCenterViewModelJavaLangObjectNull = (serviceCenterViewModel) != (null);
            if (serviceCenterViewModelJavaLangObjectNull) {


                serviceCenterViewModelAgree = serviceCenterViewModel.getAgree();

                serviceCenterViewModelAgreeJavaLangObjectNull = (serviceCenterViewModelAgree) != (null);
                if (serviceCenterViewModelAgreeJavaLangObjectNull) {




                    serviceCenterViewModelAgree.setValue(((java.lang.Boolean) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityServicecenterEtOptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of serviceCenterViewModel.category.getValue()
            //         is serviceCenterViewModel.category.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityServicecenterEtOption);
            // localize variables for thread safety
            // serviceCenterViewModel != null
            boolean serviceCenterViewModelJavaLangObjectNull = false;
            // serviceCenterViewModel.category.getValue()
            java.lang.String serviceCenterViewModelCategoryGetValue = null;
            // serviceCenterViewModel.category
            androidx.lifecycle.MutableLiveData<java.lang.String> serviceCenterViewModelCategory = null;
            // serviceCenterViewModel.category != null
            boolean serviceCenterViewModelCategoryJavaLangObjectNull = false;
            // serviceCenterViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel serviceCenterViewModel = mServiceCenterViewModel;



            serviceCenterViewModelJavaLangObjectNull = (serviceCenterViewModel) != (null);
            if (serviceCenterViewModelJavaLangObjectNull) {


                serviceCenterViewModelCategory = serviceCenterViewModel.getCategory();

                serviceCenterViewModelCategoryJavaLangObjectNull = (serviceCenterViewModelCategory) != (null);
                if (serviceCenterViewModelCategoryJavaLangObjectNull) {




                    serviceCenterViewModelCategory.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activitySevicecenterTvEmailandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of serviceCenterViewModel.email.getValue()
            //         is serviceCenterViewModel.email.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activitySevicecenterTvEmail);
            // localize variables for thread safety
            // serviceCenterViewModel.email.getValue()
            java.lang.String serviceCenterViewModelEmailGetValue = null;
            // serviceCenterViewModel.email
            androidx.lifecycle.MutableLiveData<java.lang.String> serviceCenterViewModelEmail = null;
            // serviceCenterViewModel != null
            boolean serviceCenterViewModelJavaLangObjectNull = false;
            // serviceCenterViewModel.email != null
            boolean serviceCenterViewModelEmailJavaLangObjectNull = false;
            // serviceCenterViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel serviceCenterViewModel = mServiceCenterViewModel;



            serviceCenterViewModelJavaLangObjectNull = (serviceCenterViewModel) != (null);
            if (serviceCenterViewModelJavaLangObjectNull) {


                serviceCenterViewModelEmail = serviceCenterViewModel.getEmail();

                serviceCenterViewModelEmailJavaLangObjectNull = (serviceCenterViewModelEmail) != (null);
                if (serviceCenterViewModelEmailJavaLangObjectNull) {




                    serviceCenterViewModelEmail.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentServicecenterBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentServicecenterBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6
            , (android.widget.CheckBox) bindings[4]
            , (android.widget.TextView) bindings[10]
            , (android.widget.RelativeLayout) bindings[9]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (androidx.appcompat.widget.AppCompatButton) bindings[5]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (android.widget.EditText) bindings[1]
            );
        this.activityServicecenterCheckbox.setTag(null);
        this.activityServicecenterEtContent.setTag(null);
        this.activityServicecenterEtOption.setTag(null);
        this.activityServicecenterSendBtn.setTag(null);
        this.activitySevicecenterTvEmail.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback4 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 2);
        mCallback3 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
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
        if (BR.serviceCenterFragment == variableId) {
            setServiceCenterFragment((com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.ServiceCenterFragment) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else if (BR.serviceCenterViewModel == variableId) {
            setServiceCenterViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setServiceCenterFragment(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.ServiceCenterFragment ServiceCenterFragment) {
        this.mServiceCenterFragment = ServiceCenterFragment;
    }
    public void setEditTextViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel EditTextViewModel) {
        this.mEditTextViewModel = EditTextViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.editTextViewModel);
        super.requestRebind();
    }
    public void setServiceCenterViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel ServiceCenterViewModel) {
        this.mServiceCenterViewModel = ServiceCenterViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x100L;
        }
        notifyPropertyChanged(BR.serviceCenterViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeServiceCenterViewModelEmailIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeServiceCenterViewModelEmail((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeServiceCenterViewModelCategoryIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeServiceCenterViewModelContentIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeServiceCenterViewModelCategory((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeServiceCenterViewModelAgree((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelEmailIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ServiceCenterViewModelEmailIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelEmail(androidx.lifecycle.MutableLiveData<java.lang.String> ServiceCenterViewModelEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelCategoryIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ServiceCenterViewModelCategoryIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelContentIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ServiceCenterViewModelContentIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelCategory(androidx.lifecycle.MutableLiveData<java.lang.String> ServiceCenterViewModelCategory, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeServiceCenterViewModelAgree(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ServiceCenterViewModelAgree, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        java.lang.Boolean serviceCenterViewModelEmailIsValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> serviceCenterViewModelEmailIsValid = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue = false;
        boolean serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalse = false;
        java.lang.String serviceCenterViewModelEmailGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelContentIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> serviceCenterViewModelEmail = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelEmailIsValidGetValue = false;
        boolean serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> serviceCenterViewModelCategoryIsValid = null;
        java.lang.Boolean serviceCenterViewModelContentIsValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> serviceCenterViewModelContentIsValid = null;
        java.lang.Boolean serviceCenterViewModelAgreeGetValue = null;
        java.lang.Boolean serviceCenterViewModelCategoryIsValidGetValue = null;
        java.lang.String serviceCenterViewModelCategoryGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelAgreeGetValue = false;
        boolean serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalse = false;
        android.graphics.drawable.Drawable serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseActivityServicecenterSendBtnAndroidDrawableBasicBlueBtnLayoutActivityServicecenterSendBtnAndroidDrawableBasicGrayBtnLayout = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> serviceCenterViewModelCategory = null;
        boolean serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseBooleanTrueBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> serviceCenterViewModelAgree = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel serviceCenterViewModel = mServiceCenterViewModel;
        android.graphics.drawable.Drawable serviceCenterViewModelCategoryIsValidActivityServicecenterEtOptionAndroidDrawableBasicBlackBorderLayoutActivityServicecenterEtOptionAndroidDrawableBasicGrayBorderLayout = null;

        if ((dirtyFlags & 0x33fL) != 0) {


            if ((dirtyFlags & 0x32dL) != 0) {

                    if (serviceCenterViewModel != null) {
                        // read serviceCenterViewModel.emailIsValid
                        serviceCenterViewModelEmailIsValid = serviceCenterViewModel.getEmailIsValid();
                    }
                    updateLiveDataRegistration(0, serviceCenterViewModelEmailIsValid);


                    if (serviceCenterViewModelEmailIsValid != null) {
                        // read serviceCenterViewModel.emailIsValid.getValue()
                        serviceCenterViewModelEmailIsValidGetValue = serviceCenterViewModelEmailIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelEmailIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelEmailIsValidGetValue);
                if((dirtyFlags & 0x32dL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelEmailIsValidGetValue) {
                            dirtyFlags |= 0x2000L;
                    }
                    else {
                            dirtyFlags |= 0x1000L;
                    }
                }
            }
            if ((dirtyFlags & 0x302L) != 0) {

                    if (serviceCenterViewModel != null) {
                        // read serviceCenterViewModel.email
                        serviceCenterViewModelEmail = serviceCenterViewModel.getEmail();
                    }
                    updateLiveDataRegistration(1, serviceCenterViewModelEmail);


                    if (serviceCenterViewModelEmail != null) {
                        // read serviceCenterViewModel.email.getValue()
                        serviceCenterViewModelEmailGetValue = serviceCenterViewModelEmail.getValue();
                    }
            }
            if ((dirtyFlags & 0x304L) != 0) {

                    if (serviceCenterViewModel != null) {
                        // read serviceCenterViewModel.categoryIsValid
                        serviceCenterViewModelCategoryIsValid = serviceCenterViewModel.getCategoryIsValid();
                    }
                    updateLiveDataRegistration(2, serviceCenterViewModelCategoryIsValid);


                    if (serviceCenterViewModelCategoryIsValid != null) {
                        // read serviceCenterViewModel.categoryIsValid.getValue()
                        serviceCenterViewModelCategoryIsValidGetValue = serviceCenterViewModelCategoryIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelCategoryIsValidGetValue);
                if((dirtyFlags & 0x304L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue) {
                            dirtyFlags |= 0x200000L;
                    }
                    else {
                            dirtyFlags |= 0x100000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
                    serviceCenterViewModelCategoryIsValidActivityServicecenterEtOptionAndroidDrawableBasicBlackBorderLayoutActivityServicecenterEtOptionAndroidDrawableBasicGrayBorderLayout = ((androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityServicecenterEtOption.getContext(), R.drawable.basic_black_border_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityServicecenterEtOption.getContext(), R.drawable.basic_gray_border_layout)));
            }
            if ((dirtyFlags & 0x310L) != 0) {

                    if (serviceCenterViewModel != null) {
                        // read serviceCenterViewModel.category
                        serviceCenterViewModelCategory = serviceCenterViewModel.getCategory();
                    }
                    updateLiveDataRegistration(4, serviceCenterViewModelCategory);


                    if (serviceCenterViewModelCategory != null) {
                        // read serviceCenterViewModel.category.getValue()
                        serviceCenterViewModelCategoryGetValue = serviceCenterViewModelCategory.getValue();
                    }
            }
            if ((dirtyFlags & 0x320L) != 0) {

                    if (serviceCenterViewModel != null) {
                        // read serviceCenterViewModel.agree
                        serviceCenterViewModelAgree = serviceCenterViewModel.getAgree();
                    }
                    updateLiveDataRegistration(5, serviceCenterViewModelAgree);


                    if (serviceCenterViewModelAgree != null) {
                        // read serviceCenterViewModel.agree.getValue()
                        serviceCenterViewModelAgreeGetValue = serviceCenterViewModelAgree.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelAgreeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelAgreeGetValue);
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000L) != 0) {

                if (serviceCenterViewModel != null) {
                    // read serviceCenterViewModel.categoryIsValid
                    serviceCenterViewModelCategoryIsValid = serviceCenterViewModel.getCategoryIsValid();
                }
                updateLiveDataRegistration(2, serviceCenterViewModelCategoryIsValid);


                if (serviceCenterViewModelCategoryIsValid != null) {
                    // read serviceCenterViewModel.categoryIsValid.getValue()
                    serviceCenterViewModelCategoryIsValidGetValue = serviceCenterViewModelCategoryIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelCategoryIsValidGetValue);
            if((dirtyFlags & 0x304L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue) {
                        dirtyFlags |= 0x200000L;
                }
                else {
                        dirtyFlags |= 0x100000L;
                }
            }
        }

        if ((dirtyFlags & 0x32dL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false
                serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelEmailIsValidGetValue) ? (androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelCategoryIsValidGetValue) : (false));
            if((dirtyFlags & 0x32dL) != 0) {
                if(serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalse) {
                        dirtyFlags |= 0x800L;
                }
                else {
                        dirtyFlags |= 0x400L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x800L) != 0) {

                if (serviceCenterViewModel != null) {
                    // read serviceCenterViewModel.contentIsValid
                    serviceCenterViewModelContentIsValid = serviceCenterViewModel.getContentIsValid();
                }
                updateLiveDataRegistration(3, serviceCenterViewModelContentIsValid);


                if (serviceCenterViewModelContentIsValid != null) {
                    // read serviceCenterViewModel.contentIsValid.getValue()
                    serviceCenterViewModelContentIsValidGetValue = serviceCenterViewModelContentIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelContentIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelContentIsValidGetValue);
        }

        if ((dirtyFlags & 0x32dL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false
                serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalse = ((serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelContentIsValidGetValue) : (false));
            if((dirtyFlags & 0x32dL) != 0) {
                if(serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalse) {
                        dirtyFlags |= 0x8000L;
                }
                else {
                        dirtyFlags |= 0x4000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x8000L) != 0) {

                if (serviceCenterViewModel != null) {
                    // read serviceCenterViewModel.agree
                    serviceCenterViewModelAgree = serviceCenterViewModel.getAgree();
                }
                updateLiveDataRegistration(5, serviceCenterViewModelAgree);


                if (serviceCenterViewModelAgree != null) {
                    // read serviceCenterViewModel.agree.getValue()
                    serviceCenterViewModelAgreeGetValue = serviceCenterViewModelAgree.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue())
                androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelAgreeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModelAgreeGetValue);
        }

        if ((dirtyFlags & 0x32dL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false
                serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalse = ((serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelAgreeGetValue) : (false));
            if((dirtyFlags & 0x32dL) != 0) {
                if(serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalse) {
                        dirtyFlags |= 0x20000L;
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                        dirtyFlags |= 0x40000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseActivityServicecenterSendBtnAndroidDrawableBasicBlueBtnLayoutActivityServicecenterSendBtnAndroidDrawableBasicGrayBtnLayout = ((serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityServicecenterSendBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityServicecenterSendBtn.getContext(), R.drawable.basic_gray_btn_layout)));
                // read androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? true : false
                serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseBooleanTrueBooleanFalse = ((serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalse) ? (true) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x320L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.activityServicecenterCheckbox, androidxDatabindingViewDataBindingSafeUnboxServiceCenterViewModelAgreeGetValue);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.activityServicecenterCheckbox, (android.widget.CompoundButton.OnCheckedChangeListener)null, activityServicecenterCheckboxandroidCheckedAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityServicecenterEtContent, mCallback4);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityServicecenterEtOption, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityServicecenterEtOptionandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activitySevicecenterTvEmail, mCallback3);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activitySevicecenterTvEmail, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activitySevicecenterTvEmailandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x304L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityServicecenterEtOption, serviceCenterViewModelCategoryIsValidActivityServicecenterEtOptionAndroidDrawableBasicBlackBorderLayoutActivityServicecenterEtOptionAndroidDrawableBasicGrayBorderLayout);
        }
        if ((dirtyFlags & 0x310L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityServicecenterEtOption, serviceCenterViewModelCategoryGetValue);
        }
        if ((dirtyFlags & 0x32dL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityServicecenterSendBtn, serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseActivityServicecenterSendBtnAndroidDrawableBasicBlueBtnLayoutActivityServicecenterSendBtnAndroidDrawableBasicGrayBtnLayout);
            this.activityServicecenterSendBtn.setClickable(serviceCenterViewModelEmailIsValidServiceCenterViewModelCategoryIsValidBooleanFalseServiceCenterViewModelContentIsValidBooleanFalseServiceCenterViewModelAgreeBooleanFalseBooleanTrueBooleanFalse);
        }
        if ((dirtyFlags & 0x302L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activitySevicecenterTvEmail, serviceCenterViewModelEmailGetValue);
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





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityServicecenterEtContent.getResources().getString(R.string.input_100));
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





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activitySevicecenterTvEmail.getResources().getString(R.string.response_email));
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): serviceCenterViewModel.emailIsValid
        flag 1 (0x2L): serviceCenterViewModel.email
        flag 2 (0x3L): serviceCenterViewModel.categoryIsValid
        flag 3 (0x4L): serviceCenterViewModel.contentIsValid
        flag 4 (0x5L): serviceCenterViewModel.category
        flag 5 (0x6L): serviceCenterViewModel.agree
        flag 6 (0x7L): serviceCenterFragment
        flag 7 (0x8L): editTextViewModel
        flag 8 (0x9L): serviceCenterViewModel
        flag 9 (0xaL): null
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? true : false
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.agree.getValue()) : false ? true : false
        flag 20 (0x15L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 21 (0x16L): androidx.databinding.ViewDataBinding.safeUnbox(serviceCenterViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
    flag mapping end*/
    //end
}