package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentWithdrawalBindingImpl extends FragmentWithdrawalBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_withdrawal_info, 6);
        sViewsWithIds.put(R.id.activity_withdrawal_tv_noteTitle, 7);
        sViewsWithIds.put(R.id.activity_withdrawal_tv_noteDesc, 8);
        sViewsWithIds.put(R.id.activity_withdrawal_tv_titleTitle, 9);
        sViewsWithIds.put(R.id.activity_withdrawal_tv_periodTitle, 10);
        sViewsWithIds.put(R.id.activity_withdrawal_iv_option, 11);
        sViewsWithIds.put(R.id.activity_withdrawal_checkbox_rl, 12);
        sViewsWithIds.put(R.id.activity_withdrawal_tv_checkboxDesc, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback5;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityWithdrawalCheckboxandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of withdrawalViewModel.agree.getValue()
            //         is withdrawalViewModel.agree.setValue((java.lang.Boolean) callbackArg_0)
            boolean callbackArg_0 = activityWithdrawalCheckbox.isChecked();
            // localize variables for thread safety
            // withdrawalViewModel.agree
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> withdrawalViewModelAgree = null;
            // withdrawalViewModel.agree.getValue()
            java.lang.Boolean withdrawalViewModelAgreeGetValue = null;
            // withdrawalViewModel != null
            boolean withdrawalViewModelJavaLangObjectNull = false;
            // withdrawalViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel withdrawalViewModel = mWithdrawalViewModel;
            // withdrawalViewModel.agree != null
            boolean withdrawalViewModelAgreeJavaLangObjectNull = false;



            withdrawalViewModelJavaLangObjectNull = (withdrawalViewModel) != (null);
            if (withdrawalViewModelJavaLangObjectNull) {


                withdrawalViewModelAgree = withdrawalViewModel.getAgree();

                withdrawalViewModelAgreeJavaLangObjectNull = (withdrawalViewModelAgree) != (null);
                if (withdrawalViewModelAgreeJavaLangObjectNull) {




                    withdrawalViewModelAgree.setValue(((java.lang.Boolean) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityWithdrawalEtContentandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of withdrawalViewModel.content.getValue()
            //         is withdrawalViewModel.content.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityWithdrawalEtContent);
            // localize variables for thread safety
            // withdrawalViewModel.content.getValue()
            java.lang.String withdrawalViewModelContentGetValue = null;
            // withdrawalViewModel != null
            boolean withdrawalViewModelJavaLangObjectNull = false;
            // withdrawalViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel withdrawalViewModel = mWithdrawalViewModel;
            // withdrawalViewModel.content != null
            boolean withdrawalViewModelContentJavaLangObjectNull = false;
            // withdrawalViewModel.content
            androidx.lifecycle.MutableLiveData<java.lang.String> withdrawalViewModelContent = null;



            withdrawalViewModelJavaLangObjectNull = (withdrawalViewModel) != (null);
            if (withdrawalViewModelJavaLangObjectNull) {


                withdrawalViewModelContent = withdrawalViewModel.getContent();

                withdrawalViewModelContentJavaLangObjectNull = (withdrawalViewModelContent) != (null);
                if (withdrawalViewModelContentJavaLangObjectNull) {




                    withdrawalViewModelContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityWithdrawalEtOptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of withdrawalViewModel.category.getValue()
            //         is withdrawalViewModel.category.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityWithdrawalEtOption);
            // localize variables for thread safety
            // withdrawalViewModel.category
            androidx.lifecycle.MutableLiveData<java.lang.String> withdrawalViewModelCategory = null;
            // withdrawalViewModel.category.getValue()
            java.lang.String withdrawalViewModelCategoryGetValue = null;
            // withdrawalViewModel.category != null
            boolean withdrawalViewModelCategoryJavaLangObjectNull = false;
            // withdrawalViewModel != null
            boolean withdrawalViewModelJavaLangObjectNull = false;
            // withdrawalViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel withdrawalViewModel = mWithdrawalViewModel;



            withdrawalViewModelJavaLangObjectNull = (withdrawalViewModel) != (null);
            if (withdrawalViewModelJavaLangObjectNull) {


                withdrawalViewModelCategory = withdrawalViewModel.getCategory();

                withdrawalViewModelCategoryJavaLangObjectNull = (withdrawalViewModelCategory) != (null);
                if (withdrawalViewModelCategoryJavaLangObjectNull) {




                    withdrawalViewModelCategory.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentWithdrawalBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentWithdrawalBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.CheckBox) bindings[4]
            , (android.widget.RelativeLayout) bindings[12]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (android.widget.ImageView) bindings[11]
            , (androidx.appcompat.widget.AppCompatButton) bindings[5]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[9]
            );
        this.activityWithdrawalCheckbox.setTag(null);
        this.activityWithdrawalEtContent.setTag(null);
        this.activityWithdrawalEtOption.setTag(null);
        this.activityWithdrawalSendBtn.setTag(null);
        this.activityWithdrawalTvTitleDesc.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x400L;
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
        if (BR.withdrawalViewModel == variableId) {
            setWithdrawalViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel) variable);
        }
        else if (BR.withdrawalFragment == variableId) {
            setWithdrawalFragment((com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.WithdrawalFragment) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setWithdrawalViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel WithdrawalViewModel) {
        this.mWithdrawalViewModel = WithdrawalViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.withdrawalViewModel);
        super.requestRebind();
    }
    public void setWithdrawalFragment(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.WithdrawalFragment WithdrawalFragment) {
        this.mWithdrawalFragment = WithdrawalFragment;
    }
    public void setEditTextViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel EditTextViewModel) {
        this.mEditTextViewModel = EditTextViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x200L;
        }
        notifyPropertyChanged(BR.editTextViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeWithdrawalViewModelAgree((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeWithdrawalViewModelEmailIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeWithdrawalViewModelCategoryIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeWithdrawalViewModelContentIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeWithdrawalViewModelCategory((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeWithdrawalViewModelEmail((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeWithdrawalViewModelContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelAgree(androidx.lifecycle.MutableLiveData<java.lang.Boolean> WithdrawalViewModelAgree, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelEmailIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> WithdrawalViewModelEmailIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelCategoryIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> WithdrawalViewModelCategoryIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelContentIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> WithdrawalViewModelContentIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelCategory(androidx.lifecycle.MutableLiveData<java.lang.String> WithdrawalViewModelCategory, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelEmail(androidx.lifecycle.MutableLiveData<java.lang.String> WithdrawalViewModelEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeWithdrawalViewModelContent(androidx.lifecycle.MutableLiveData<java.lang.String> WithdrawalViewModelContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
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
        java.lang.Boolean withdrawalViewModelContentIsValidGetValue = null;
        boolean withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalse = false;
        boolean withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalse = false;
        boolean withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> withdrawalViewModelAgree = null;
        android.graphics.drawable.Drawable withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseActivityWithdrawalSendBtnAndroidDrawableBasicBlueBtnLayoutActivityWithdrawalSendBtnAndroidDrawableBasicGrayBtnLayout = null;
        boolean withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseBooleanTrueBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> withdrawalViewModelEmailIsValid = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> withdrawalViewModelCategoryIsValid = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> withdrawalViewModelContentIsValid = null;
        java.lang.String withdrawalViewModelEmailGetValue = null;
        java.lang.String withdrawalViewModelCategoryGetValue = null;
        int withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtcViewVISIBLEViewGONE = 0;
        android.graphics.drawable.Drawable withdrawalViewModelCategoryIsValidActivityWithdrawalEtOptionAndroidDrawableBasicBlackBorderLayoutActivityWithdrawalEtOptionAndroidDrawableBasicGrayBorderLayout = null;
        boolean withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtc = false;
        java.lang.String withdrawalViewModelContentGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel withdrawalViewModel = mWithdrawalViewModel;
        java.lang.Boolean withdrawalViewModelEmailIsValidGetValue = null;
        java.lang.Boolean withdrawalViewModelCategoryIsValidGetValue = null;
        java.lang.Boolean withdrawalViewModelAgreeGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelAgreeGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelEmailIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> withdrawalViewModelCategory = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> withdrawalViewModelEmail = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> withdrawalViewModelContent = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelContentIsValidGetValue = false;

        if ((dirtyFlags & 0x4ffL) != 0) {


            if ((dirtyFlags & 0x481L) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.agree
                        withdrawalViewModelAgree = withdrawalViewModel.getAgree();
                    }
                    updateLiveDataRegistration(0, withdrawalViewModelAgree);


                    if (withdrawalViewModelAgree != null) {
                        // read withdrawalViewModel.agree.getValue()
                        withdrawalViewModelAgreeGetValue = withdrawalViewModelAgree.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelAgreeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelAgreeGetValue);
            }
            if ((dirtyFlags & 0x48fL) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.emailIsValid
                        withdrawalViewModelEmailIsValid = withdrawalViewModel.getEmailIsValid();
                    }
                    updateLiveDataRegistration(1, withdrawalViewModelEmailIsValid);


                    if (withdrawalViewModelEmailIsValid != null) {
                        // read withdrawalViewModel.emailIsValid.getValue()
                        withdrawalViewModelEmailIsValidGetValue = withdrawalViewModelEmailIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelEmailIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelEmailIsValidGetValue);
                if((dirtyFlags & 0x48fL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelEmailIsValidGetValue) {
                            dirtyFlags |= 0x4000L;
                    }
                    else {
                            dirtyFlags |= 0x2000L;
                    }
                }
            }
            if ((dirtyFlags & 0x484L) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.categoryIsValid
                        withdrawalViewModelCategoryIsValid = withdrawalViewModel.getCategoryIsValid();
                    }
                    updateLiveDataRegistration(2, withdrawalViewModelCategoryIsValid);


                    if (withdrawalViewModelCategoryIsValid != null) {
                        // read withdrawalViewModel.categoryIsValid.getValue()
                        withdrawalViewModelCategoryIsValidGetValue = withdrawalViewModelCategoryIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelCategoryIsValidGetValue);
                if((dirtyFlags & 0x484L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue) {
                            dirtyFlags |= 0x1000000L;
                    }
                    else {
                            dirtyFlags |= 0x800000L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
                    withdrawalViewModelCategoryIsValidActivityWithdrawalEtOptionAndroidDrawableBasicBlackBorderLayoutActivityWithdrawalEtOptionAndroidDrawableBasicGrayBorderLayout = ((androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityWithdrawalEtOption.getContext(), R.drawable.basic_black_border_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityWithdrawalEtOption.getContext(), R.drawable.basic_gray_border_layout)));
            }
            if ((dirtyFlags & 0x490L) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.category
                        withdrawalViewModelCategory = withdrawalViewModel.getCategory();
                    }
                    updateLiveDataRegistration(4, withdrawalViewModelCategory);


                    if (withdrawalViewModelCategory != null) {
                        // read withdrawalViewModel.category.getValue()
                        withdrawalViewModelCategoryGetValue = withdrawalViewModelCategory.getValue();
                    }


                    if (withdrawalViewModelCategoryGetValue != null) {
                        // read withdrawalViewModel.category.getValue().equals(@android:string/etc)
                        withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtc = withdrawalViewModelCategoryGetValue.equals(activityWithdrawalEtContent.getResources().getString(R.string.etc));
                    }
                if((dirtyFlags & 0x490L) != 0) {
                    if(withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtc) {
                            dirtyFlags |= 0x400000L;
                    }
                    else {
                            dirtyFlags |= 0x200000L;
                    }
                }


                    // read withdrawalViewModel.category.getValue().equals(@android:string/etc) ? View.VISIBLE : View.GONE
                    withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtcViewVISIBLEViewGONE = ((withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtc) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x4a0L) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.email
                        withdrawalViewModelEmail = withdrawalViewModel.getEmail();
                    }
                    updateLiveDataRegistration(5, withdrawalViewModelEmail);


                    if (withdrawalViewModelEmail != null) {
                        // read withdrawalViewModel.email.getValue()
                        withdrawalViewModelEmailGetValue = withdrawalViewModelEmail.getValue();
                    }
            }
            if ((dirtyFlags & 0x4c0L) != 0) {

                    if (withdrawalViewModel != null) {
                        // read withdrawalViewModel.content
                        withdrawalViewModelContent = withdrawalViewModel.getContent();
                    }
                    updateLiveDataRegistration(6, withdrawalViewModelContent);


                    if (withdrawalViewModelContent != null) {
                        // read withdrawalViewModel.content.getValue()
                        withdrawalViewModelContentGetValue = withdrawalViewModelContent.getValue();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x4000L) != 0) {

                if (withdrawalViewModel != null) {
                    // read withdrawalViewModel.categoryIsValid
                    withdrawalViewModelCategoryIsValid = withdrawalViewModel.getCategoryIsValid();
                }
                updateLiveDataRegistration(2, withdrawalViewModelCategoryIsValid);


                if (withdrawalViewModelCategoryIsValid != null) {
                    // read withdrawalViewModel.categoryIsValid.getValue()
                    withdrawalViewModelCategoryIsValidGetValue = withdrawalViewModelCategoryIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelCategoryIsValidGetValue);
            if((dirtyFlags & 0x484L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue) {
                        dirtyFlags |= 0x1000000L;
                }
                else {
                        dirtyFlags |= 0x800000L;
                }
            }
        }

        if ((dirtyFlags & 0x48fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false
                withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelEmailIsValidGetValue) ? (androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelCategoryIsValidGetValue) : (false));
            if((dirtyFlags & 0x48fL) != 0) {
                if(withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalse) {
                        dirtyFlags |= 0x1000L;
                }
                else {
                        dirtyFlags |= 0x800L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x1000L) != 0) {

                if (withdrawalViewModel != null) {
                    // read withdrawalViewModel.contentIsValid
                    withdrawalViewModelContentIsValid = withdrawalViewModel.getContentIsValid();
                }
                updateLiveDataRegistration(3, withdrawalViewModelContentIsValid);


                if (withdrawalViewModelContentIsValid != null) {
                    // read withdrawalViewModel.contentIsValid.getValue()
                    withdrawalViewModelContentIsValidGetValue = withdrawalViewModelContentIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelContentIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelContentIsValidGetValue);
        }

        if ((dirtyFlags & 0x48fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false
                withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalse = ((withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelContentIsValidGetValue) : (false));
            if((dirtyFlags & 0x48fL) != 0) {
                if(withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalse) {
                        dirtyFlags |= 0x10000L;
                }
                else {
                        dirtyFlags |= 0x8000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x10000L) != 0) {

                if (withdrawalViewModel != null) {
                    // read withdrawalViewModel.agree
                    withdrawalViewModelAgree = withdrawalViewModel.getAgree();
                }
                updateLiveDataRegistration(0, withdrawalViewModelAgree);


                if (withdrawalViewModelAgree != null) {
                    // read withdrawalViewModel.agree.getValue()
                    withdrawalViewModelAgreeGetValue = withdrawalViewModelAgree.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue())
                androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelAgreeGetValue = androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModelAgreeGetValue);
        }

        if ((dirtyFlags & 0x48fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false
                withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalse = ((withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelAgreeGetValue) : (false));
            if((dirtyFlags & 0x48fL) != 0) {
                if(withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalse) {
                        dirtyFlags |= 0x40000L;
                        dirtyFlags |= 0x100000L;
                }
                else {
                        dirtyFlags |= 0x20000L;
                        dirtyFlags |= 0x80000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseActivityWithdrawalSendBtnAndroidDrawableBasicBlueBtnLayoutActivityWithdrawalSendBtnAndroidDrawableBasicGrayBtnLayout = ((withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityWithdrawalSendBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityWithdrawalSendBtn.getContext(), R.drawable.basic_gray_btn_layout)));
                // read androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? true : false
                withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseBooleanTrueBooleanFalse = ((withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalse) ? (true) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x481L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.activityWithdrawalCheckbox, androidxDatabindingViewDataBindingSafeUnboxWithdrawalViewModelAgreeGetValue);
        }
        if ((dirtyFlags & 0x400L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.activityWithdrawalCheckbox, (android.widget.CompoundButton.OnCheckedChangeListener)null, activityWithdrawalCheckboxandroidCheckedAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityWithdrawalEtContent, mCallback5);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityWithdrawalEtContent, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityWithdrawalEtContentandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityWithdrawalEtOption, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityWithdrawalEtOptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityWithdrawalSendBtn, (activityWithdrawalSendBtn.getResources().getString(R.string.withdrawal)) + (activityWithdrawalSendBtn.getResources().getString(R.string.doing)));
        }
        if ((dirtyFlags & 0x4c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityWithdrawalEtContent, withdrawalViewModelContentGetValue);
        }
        if ((dirtyFlags & 0x490L) != 0) {
            // api target 1

            this.activityWithdrawalEtContent.setVisibility(withdrawalViewModelCategoryEqualsActivityWithdrawalEtContentAndroidStringEtcViewVISIBLEViewGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityWithdrawalEtOption, withdrawalViewModelCategoryGetValue);
        }
        if ((dirtyFlags & 0x484L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityWithdrawalEtOption, withdrawalViewModelCategoryIsValidActivityWithdrawalEtOptionAndroidDrawableBasicBlackBorderLayoutActivityWithdrawalEtOptionAndroidDrawableBasicGrayBorderLayout);
        }
        if ((dirtyFlags & 0x48fL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityWithdrawalSendBtn, withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseActivityWithdrawalSendBtnAndroidDrawableBasicBlueBtnLayoutActivityWithdrawalSendBtnAndroidDrawableBasicGrayBtnLayout);
            this.activityWithdrawalSendBtn.setClickable(withdrawalViewModelEmailIsValidWithdrawalViewModelCategoryIsValidBooleanFalseWithdrawalViewModelContentIsValidBooleanFalseWithdrawalViewModelAgreeBooleanFalseBooleanTrueBooleanFalse);
        }
        if ((dirtyFlags & 0x4a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityWithdrawalTvTitleDesc, withdrawalViewModelEmailGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnFocusLost(int sourceId , android.widget.EditText callbackArg_0, boolean callbackArg_1) {
        // localize variables for thread safety
        // editTextViewModel != null
        boolean editTextViewModelJavaLangObjectNull = false;
        // editTextViewModel
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



        editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
        if (editTextViewModelJavaLangObjectNull) {





            editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityWithdrawalEtContent.getResources().getString(R.string.input_100));
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): withdrawalViewModel.agree
        flag 1 (0x2L): withdrawalViewModel.emailIsValid
        flag 2 (0x3L): withdrawalViewModel.categoryIsValid
        flag 3 (0x4L): withdrawalViewModel.contentIsValid
        flag 4 (0x5L): withdrawalViewModel.category
        flag 5 (0x6L): withdrawalViewModel.email
        flag 6 (0x7L): withdrawalViewModel.content
        flag 7 (0x8L): withdrawalViewModel
        flag 8 (0x9L): withdrawalFragment
        flag 9 (0xaL): editTextViewModel
        flag 10 (0xbL): null
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? true : false
        flag 20 (0x15L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.emailIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.contentIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.agree.getValue()) : false ? true : false
        flag 21 (0x16L): withdrawalViewModel.category.getValue().equals(@android:string/etc) ? View.VISIBLE : View.GONE
        flag 22 (0x17L): withdrawalViewModel.category.getValue().equals(@android:string/etc) ? View.VISIBLE : View.GONE
        flag 23 (0x18L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
        flag 24 (0x19L): androidx.databinding.ViewDataBinding.safeUnbox(withdrawalViewModel.categoryIsValid.getValue()) ? @android:drawable/basic_black_border_layout : @android:drawable/basic_gray_border_layout
    flag mapping end*/
    //end
}