package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentRegisterAuthenticationBindingImpl extends FragmentRegisterAuthenticationBinding implements com.garamgaebi.garamgaebi.generated.callback.OnClickListener.Listener, com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView, 5);
        sViewsWithIds.put(R.id.fragment_authentication_title, 6);
        sViewsWithIds.put(R.id.fragment_authentication_desc, 7);
        sViewsWithIds.put(R.id.fragment_authentication_tv_email_title, 8);
        sViewsWithIds.put(R.id.fragment_authentication_domain, 9);
        sViewsWithIds.put(R.id.fragment_authentication_tv_ann, 10);
        sViewsWithIds.put(R.id.fragment_authentication_tv_num_ann, 11);
        sViewsWithIds.put(R.id.fragment_authentication_btn, 12);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback13;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback14;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback12;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener fragmentAuthenticationEtEmailandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of registerViewModel.email.getValue()
            //         is registerViewModel.email.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(fragmentAuthenticationEtEmail);
            // localize variables for thread safety
            // registerViewModel.email.getValue()
            java.lang.String registerViewModelEmailGetValue = null;
            // registerViewModel != null
            boolean registerViewModelJavaLangObjectNull = false;
            // registerViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel registerViewModel = mRegisterViewModel;
            // registerViewModel.email
            androidx.lifecycle.MutableLiveData<java.lang.String> registerViewModelEmail = null;
            // registerViewModel.email != null
            boolean registerViewModelEmailJavaLangObjectNull = false;



            registerViewModelJavaLangObjectNull = (registerViewModel) != (null);
            if (registerViewModelJavaLangObjectNull) {


                registerViewModelEmail = registerViewModel.getEmail();

                registerViewModelEmailJavaLangObjectNull = (registerViewModelEmail) != (null);
                if (registerViewModelEmailJavaLangObjectNull) {




                    registerViewModelEmail.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener fragmentAuthenticationEtNumandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of registerViewModel.authNum.getValue()
            //         is registerViewModel.authNum.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(fragmentAuthenticationEtNum);
            // localize variables for thread safety
            // registerViewModel.authNum.getValue()
            java.lang.String registerViewModelAuthNumGetValue = null;
            // registerViewModel != null
            boolean registerViewModelJavaLangObjectNull = false;
            // registerViewModel.authNum != null
            boolean registerViewModelAuthNumJavaLangObjectNull = false;
            // registerViewModel.authNum
            androidx.lifecycle.MutableLiveData<java.lang.String> registerViewModelAuthNum = null;
            // registerViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel registerViewModel = mRegisterViewModel;



            registerViewModelJavaLangObjectNull = (registerViewModel) != (null);
            if (registerViewModelJavaLangObjectNull) {


                registerViewModelAuthNum = registerViewModel.getAuthNum();

                registerViewModelAuthNumJavaLangObjectNull = (registerViewModelAuthNum) != (null);
                if (registerViewModelAuthNumJavaLangObjectNull) {




                    registerViewModelAuthNum.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentRegisterAuthenticationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private FragmentRegisterAuthenticationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[11]
            , (android.widget.ImageView) bindings[5]
            );
        this.fragmentAuthenticationBtnEmail.setTag(null);
        this.fragmentAuthenticationBtnNum.setTag(null);
        this.fragmentAuthenticationEtEmail.setTag(null);
        this.fragmentAuthenticationEtNum.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback13 = new com.garamgaebi.garamgaebi.generated.callback.OnClickListener(this, 2);
        mCallback14 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 3);
        mCallback12 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
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
        if (BR.registerViewModel == variableId) {
            setRegisterViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel) variable);
        }
        else if (BR.context == variableId) {
            setContext((com.garamgaebi.garamgaebi.garamgaebi.src.main.register.RegisterActivity) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRegisterViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel RegisterViewModel) {
        this.mRegisterViewModel = RegisterViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.registerViewModel);
        super.requestRebind();
    }
    public void setContext(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.register.RegisterActivity Context) {
        this.mContext = Context;
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
                return onChangeRegisterViewModelIsEmailValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeRegisterViewModelIsNumValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeRegisterViewModelEmail((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeRegisterViewModelAuthNum((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeRegisterViewModelIsEmailValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> RegisterViewModelIsEmailValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRegisterViewModelIsNumValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> RegisterViewModelIsNumValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRegisterViewModelEmail(androidx.lifecycle.MutableLiveData<java.lang.String> RegisterViewModelEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeRegisterViewModelAuthNum(androidx.lifecycle.MutableLiveData<java.lang.String> RegisterViewModelAuthNum, int fieldId) {
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
        boolean registerViewModelIsEmailValidBooleanTrueBooleanFalse = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsNumValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> registerViewModelIsEmailValid = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> registerViewModelIsNumValid = null;
        boolean registerViewModelIsNumValidBooleanTrueBooleanFalse = false;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel registerViewModel = mRegisterViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> registerViewModelEmail = null;
        java.lang.String registerViewModelEmailGetValue = null;
        java.lang.String registerViewModelAuthNumGetValue = null;
        java.lang.Boolean registerViewModelIsEmailValidGetValue = null;
        java.lang.Boolean registerViewModelIsNumValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> registerViewModelAuthNum = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;
        boolean androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsEmailValidGetValue = false;

        if ((dirtyFlags & 0x9fL) != 0) {


            if ((dirtyFlags & 0x91L) != 0) {

                    if (registerViewModel != null) {
                        // read registerViewModel.isEmailValid()
                        registerViewModelIsEmailValid = registerViewModel.isEmailValid();
                    }
                    updateLiveDataRegistration(0, registerViewModelIsEmailValid);


                    if (registerViewModelIsEmailValid != null) {
                        // read registerViewModel.isEmailValid().getValue()
                        registerViewModelIsEmailValidGetValue = registerViewModelIsEmailValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isEmailValid().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsEmailValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(registerViewModelIsEmailValidGetValue);
                if((dirtyFlags & 0x91L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsEmailValidGetValue) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isEmailValid().getValue()) ? true : false
                    registerViewModelIsEmailValidBooleanTrueBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsEmailValidGetValue) ? (true) : (false));
            }
            if ((dirtyFlags & 0x92L) != 0) {

                    if (registerViewModel != null) {
                        // read registerViewModel.isNumValid()
                        registerViewModelIsNumValid = registerViewModel.isNumValid();
                    }
                    updateLiveDataRegistration(1, registerViewModelIsNumValid);


                    if (registerViewModelIsNumValid != null) {
                        // read registerViewModel.isNumValid().getValue()
                        registerViewModelIsNumValidGetValue = registerViewModelIsNumValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isNumValid().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsNumValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(registerViewModelIsNumValidGetValue);
                if((dirtyFlags & 0x92L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsNumValidGetValue) {
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isNumValid().getValue()) ? true : false
                    registerViewModelIsNumValidBooleanTrueBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxRegisterViewModelIsNumValidGetValue) ? (true) : (false));
            }
            if ((dirtyFlags & 0x94L) != 0) {

                    if (registerViewModel != null) {
                        // read registerViewModel.email
                        registerViewModelEmail = registerViewModel.getEmail();
                    }
                    updateLiveDataRegistration(2, registerViewModelEmail);


                    if (registerViewModelEmail != null) {
                        // read registerViewModel.email.getValue()
                        registerViewModelEmailGetValue = registerViewModelEmail.getValue();
                    }
            }
            if ((dirtyFlags & 0x98L) != 0) {

                    if (registerViewModel != null) {
                        // read registerViewModel.authNum
                        registerViewModelAuthNum = registerViewModel.getAuthNum();
                    }
                    updateLiveDataRegistration(3, registerViewModelAuthNum);


                    if (registerViewModelAuthNum != null) {
                        // read registerViewModel.authNum.getValue()
                        registerViewModelAuthNumGetValue = registerViewModelAuthNum.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x91L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setOnClick(this.fragmentAuthenticationBtnEmail, mCallback13, registerViewModelIsEmailValidBooleanTrueBooleanFalse);
        }
        if ((dirtyFlags & 0x92L) != 0) {
            // api target 1

            this.fragmentAuthenticationBtnNum.setEnabled(registerViewModelIsNumValidBooleanTrueBooleanFalse);
        }
        if ((dirtyFlags & 0x94L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.fragmentAuthenticationEtEmail, registerViewModelEmailGetValue);
        }
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.fragmentAuthenticationEtEmail, mCallback12);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.fragmentAuthenticationEtEmail, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, fragmentAuthenticationEtEmailandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.fragmentAuthenticationEtNum, mCallback14);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.fragmentAuthenticationEtNum, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, fragmentAuthenticationEtNumandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x98L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.fragmentAuthenticationEtNum, registerViewModelAuthNumGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // registerViewModel != null
        boolean registerViewModelJavaLangObjectNull = false;
        // registerViewModel
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.RegisterViewModel registerViewModel = mRegisterViewModel;



        registerViewModelJavaLangObjectNull = (registerViewModel) != (null);
        if (registerViewModelJavaLangObjectNull) {


            registerViewModel.timerStart();
        }
    }
    public final void _internalCallbackOnFocusLost(int sourceId , android.widget.EditText callbackArg_0, boolean callbackArg_1) {
        switch(sourceId) {
            case 3: {
                // localize variables for thread safety
                // editTextViewModel != null
                boolean editTextViewModelJavaLangObjectNull = false;
                // editTextViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



                editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
                if (editTextViewModelJavaLangObjectNull) {





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, fragmentAuthenticationEtNum.getResources().getString(R.string.example));
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





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, fragmentAuthenticationEtEmail.getResources().getString(R.string.example));
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): registerViewModel.isEmailValid()
        flag 1 (0x2L): registerViewModel.isNumValid()
        flag 2 (0x3L): registerViewModel.email
        flag 3 (0x4L): registerViewModel.authNum
        flag 4 (0x5L): registerViewModel
        flag 5 (0x6L): context
        flag 6 (0x7L): editTextViewModel
        flag 7 (0x8L): null
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isEmailValid().getValue()) ? true : false
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isEmailValid().getValue()) ? true : false
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isNumValid().getValue()) ? true : false
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(registerViewModel.isNumValid().getValue()) ? true : false
    flag mapping end*/
    //end
}