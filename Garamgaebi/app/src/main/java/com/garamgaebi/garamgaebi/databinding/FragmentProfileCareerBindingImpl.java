package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileCareerBindingImpl extends FragmentProfileCareerBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_career_info, 5);
        sViewsWithIds.put(R.id.activity_career_tv_companyTitle, 6);
        sViewsWithIds.put(R.id.caution_input_company_tv, 7);
        sViewsWithIds.put(R.id.activity_career_tv_positionTitle, 8);
        sViewsWithIds.put(R.id.caution_input_position_tv, 9);
        sViewsWithIds.put(R.id.activity_career_tv_periodTitle, 10);
        sViewsWithIds.put(R.id.activity_career_tv_period, 11);
        sViewsWithIds.put(R.id.activity_career_et_endPeriod, 12);
        sViewsWithIds.put(R.id.activity_career_checkbox_rl, 13);
        sViewsWithIds.put(R.id.activity_career_checkbox, 14);
        sViewsWithIds.put(R.id.activity_career_checkbox_desc, 15);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback2;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityCareerEtCompanyDescandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of careerViewModel.company.getValue()
            //         is careerViewModel.company.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityCareerEtCompanyDesc);
            // localize variables for thread safety
            // careerViewModel != null
            boolean careerViewModelJavaLangObjectNull = false;
            // careerViewModel.company
            androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelCompany = null;
            // careerViewModel.company != null
            boolean careerViewModelCompanyJavaLangObjectNull = false;
            // careerViewModel.company.getValue()
            java.lang.String careerViewModelCompanyGetValue = null;
            // careerViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel careerViewModel = mCareerViewModel;



            careerViewModelJavaLangObjectNull = (careerViewModel) != (null);
            if (careerViewModelJavaLangObjectNull) {


                careerViewModelCompany = careerViewModel.getCompany();

                careerViewModelCompanyJavaLangObjectNull = (careerViewModelCompany) != (null);
                if (careerViewModelCompanyJavaLangObjectNull) {




                    careerViewModelCompany.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityCareerEtPositionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of careerViewModel.position.getValue()
            //         is careerViewModel.position.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityCareerEtPosition);
            // localize variables for thread safety
            // careerViewModel.position != null
            boolean careerViewModelPositionJavaLangObjectNull = false;
            // careerViewModel.position.getValue()
            java.lang.String careerViewModelPositionGetValue = null;
            // careerViewModel != null
            boolean careerViewModelJavaLangObjectNull = false;
            // careerViewModel.position
            androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelPosition = null;
            // careerViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel careerViewModel = mCareerViewModel;



            careerViewModelJavaLangObjectNull = (careerViewModel) != (null);
            if (careerViewModelJavaLangObjectNull) {


                careerViewModelPosition = careerViewModel.getPosition();

                careerViewModelPositionJavaLangObjectNull = (careerViewModelPosition) != (null);
                if (careerViewModelPositionJavaLangObjectNull) {




                    careerViewModelPosition.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityCareerEtStartPeriodandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of careerViewModel.startDate.getValue()
            //         is careerViewModel.startDate.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityCareerEtStartPeriod);
            // localize variables for thread safety
            // careerViewModel != null
            boolean careerViewModelJavaLangObjectNull = false;
            // careerViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel careerViewModel = mCareerViewModel;
            // careerViewModel.startDate != null
            boolean careerViewModelStartDateJavaLangObjectNull = false;
            // careerViewModel.startDate
            androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelStartDate = null;
            // careerViewModel.startDate.getValue()
            java.lang.String careerViewModelStartDateGetValue = null;



            careerViewModelJavaLangObjectNull = (careerViewModel) != (null);
            if (careerViewModelJavaLangObjectNull) {


                careerViewModelStartDate = careerViewModel.getStartDate();

                careerViewModelStartDateJavaLangObjectNull = (careerViewModelStartDate) != (null);
                if (careerViewModelStartDateJavaLangObjectNull) {




                    careerViewModelStartDate.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentProfileCareerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentProfileCareerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.CheckBox) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.RelativeLayout) bindings[13]
            , (android.widget.EditText) bindings[1]
            , (android.widget.TextView) bindings[12]
            , (android.widget.EditText) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.appcompat.widget.AppCompatButton) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            );
        this.activityCareerEtCompanyDesc.setTag(null);
        this.activityCareerEtPosition.setTag(null);
        this.activityCareerEtStartPeriod.setTag(null);
        this.activityCareerSaveBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 2);
        mCallback1 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
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
        if (BR.careerViewModel == variableId) {
            setCareerViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCareerViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel CareerViewModel) {
        this.mCareerViewModel = CareerViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.careerViewModel);
        super.requestRebind();
    }
    public void setEditTextViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel EditTextViewModel) {
        this.mEditTextViewModel = EditTextViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x100L;
        }
        notifyPropertyChanged(BR.editTextViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeCareerViewModelPositionIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeCareerViewModelCompanyIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeCareerViewModelStartDateIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeCareerViewModelEndDateIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeCareerViewModelStartDate((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeCareerViewModelCompany((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeCareerViewModelPosition((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeCareerViewModelPositionIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> CareerViewModelPositionIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelCompanyIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> CareerViewModelCompanyIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelStartDateIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> CareerViewModelStartDateIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelEndDateIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> CareerViewModelEndDateIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelStartDate(androidx.lifecycle.MutableLiveData<java.lang.String> CareerViewModelStartDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelCompany(androidx.lifecycle.MutableLiveData<java.lang.String> CareerViewModelCompany, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCareerViewModelPosition(androidx.lifecycle.MutableLiveData<java.lang.String> CareerViewModelPosition, int fieldId) {
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
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> careerViewModelPositionIsValid = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> careerViewModelCompanyIsValid = null;
        boolean careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalse = false;
        java.lang.Boolean careerViewModelStartDateIsValidGetValue = null;
        android.graphics.drawable.Drawable careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseActivityCareerSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityCareerSaveBtnAndroidDrawableBasicGrayBtnLayout = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxCareerViewModelPositionIsValidGetValue = false;
        java.lang.Boolean careerViewModelEndDateIsValidGetValue = null;
        boolean careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxCareerViewModelStartDateIsValidGetValue = false;
        java.lang.String careerViewModelStartDateGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> careerViewModelStartDateIsValid = null;
        java.lang.String careerViewModelPositionGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxCareerViewModelCompanyIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> careerViewModelEndDateIsValid = null;
        java.lang.Boolean careerViewModelPositionIsValidGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxCareerViewModelEndDateIsValidGetValue = false;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.CareerViewModel careerViewModel = mCareerViewModel;
        androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelStartDate = null;
        boolean careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelCompany = null;
        java.lang.String careerViewModelCompanyGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> careerViewModelPosition = null;
        boolean careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalse = false;
        java.lang.Boolean careerViewModelCompanyIsValidGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;

        if ((dirtyFlags & 0x2ffL) != 0) {


            if ((dirtyFlags & 0x28fL) != 0) {

                    if (careerViewModel != null) {
                        // read careerViewModel.companyIsValid
                        careerViewModelCompanyIsValid = careerViewModel.getCompanyIsValid();
                    }
                    updateLiveDataRegistration(1, careerViewModelCompanyIsValid);


                    if (careerViewModelCompanyIsValid != null) {
                        // read careerViewModel.companyIsValid.getValue()
                        careerViewModelCompanyIsValidGetValue = careerViewModelCompanyIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxCareerViewModelCompanyIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(careerViewModelCompanyIsValidGetValue);
                if((dirtyFlags & 0x28fL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxCareerViewModelCompanyIsValidGetValue) {
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                    }
                }
            }
            if ((dirtyFlags & 0x290L) != 0) {

                    if (careerViewModel != null) {
                        // read careerViewModel.startDate
                        careerViewModelStartDate = careerViewModel.getStartDate();
                    }
                    updateLiveDataRegistration(4, careerViewModelStartDate);


                    if (careerViewModelStartDate != null) {
                        // read careerViewModel.startDate.getValue()
                        careerViewModelStartDateGetValue = careerViewModelStartDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x2a0L) != 0) {

                    if (careerViewModel != null) {
                        // read careerViewModel.company
                        careerViewModelCompany = careerViewModel.getCompany();
                    }
                    updateLiveDataRegistration(5, careerViewModelCompany);


                    if (careerViewModelCompany != null) {
                        // read careerViewModel.company.getValue()
                        careerViewModelCompanyGetValue = careerViewModelCompany.getValue();
                    }
            }
            if ((dirtyFlags & 0x2c0L) != 0) {

                    if (careerViewModel != null) {
                        // read careerViewModel.position
                        careerViewModelPosition = careerViewModel.getPosition();
                    }
                    updateLiveDataRegistration(6, careerViewModelPosition);


                    if (careerViewModelPosition != null) {
                        // read careerViewModel.position.getValue()
                        careerViewModelPositionGetValue = careerViewModelPosition.getValue();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x800L) != 0) {

                if (careerViewModel != null) {
                    // read careerViewModel.positionIsValid
                    careerViewModelPositionIsValid = careerViewModel.getPositionIsValid();
                }
                updateLiveDataRegistration(0, careerViewModelPositionIsValid);


                if (careerViewModelPositionIsValid != null) {
                    // read careerViewModel.positionIsValid.getValue()
                    careerViewModelPositionIsValidGetValue = careerViewModelPositionIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxCareerViewModelPositionIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(careerViewModelPositionIsValidGetValue);
        }

        if ((dirtyFlags & 0x28fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false
                careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxCareerViewModelCompanyIsValidGetValue) ? (androidxDatabindingViewDataBindingSafeUnboxCareerViewModelPositionIsValidGetValue) : (false));
            if((dirtyFlags & 0x28fL) != 0) {
                if(careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalse) {
                        dirtyFlags |= 0x20000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x20000L) != 0) {

                if (careerViewModel != null) {
                    // read careerViewModel.startDateIsValid
                    careerViewModelStartDateIsValid = careerViewModel.getStartDateIsValid();
                }
                updateLiveDataRegistration(2, careerViewModelStartDateIsValid);


                if (careerViewModelStartDateIsValid != null) {
                    // read careerViewModel.startDateIsValid.getValue()
                    careerViewModelStartDateIsValidGetValue = careerViewModelStartDateIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxCareerViewModelStartDateIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(careerViewModelStartDateIsValidGetValue);
        }

        if ((dirtyFlags & 0x28fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false
                careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalse = ((careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxCareerViewModelStartDateIsValidGetValue) : (false));
            if((dirtyFlags & 0x28fL) != 0) {
                if(careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalse) {
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x40000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x80000L) != 0) {

                if (careerViewModel != null) {
                    // read careerViewModel.endDateIsValid
                    careerViewModelEndDateIsValid = careerViewModel.getEndDateIsValid();
                }
                updateLiveDataRegistration(3, careerViewModelEndDateIsValid);


                if (careerViewModelEndDateIsValid != null) {
                    // read careerViewModel.endDateIsValid.getValue()
                    careerViewModelEndDateIsValidGetValue = careerViewModelEndDateIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxCareerViewModelEndDateIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(careerViewModelEndDateIsValidGetValue);
        }

        if ((dirtyFlags & 0x28fL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false
                careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalse = ((careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxCareerViewModelEndDateIsValidGetValue) : (false));
            if((dirtyFlags & 0x28fL) != 0) {
                if(careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalse) {
                        dirtyFlags |= 0x2000L;
                        dirtyFlags |= 0x8000L;
                }
                else {
                        dirtyFlags |= 0x1000L;
                        dirtyFlags |= 0x4000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseActivityCareerSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityCareerSaveBtnAndroidDrawableBasicGrayBtnLayout = ((careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityCareerSaveBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityCareerSaveBtn.getContext(), R.drawable.basic_gray_btn_layout)));
                // read androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? true : false
                careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse = ((careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalse) ? (true) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x2a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityCareerEtCompanyDesc, careerViewModelCompanyGetValue);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityCareerEtCompanyDesc, mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityCareerEtCompanyDesc, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityCareerEtCompanyDescandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityCareerEtPosition, mCallback2);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityCareerEtPosition, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityCareerEtPositionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityCareerEtStartPeriod, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityCareerEtStartPeriodandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x2c0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityCareerEtPosition, careerViewModelPositionGetValue);
        }
        if ((dirtyFlags & 0x290L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityCareerEtStartPeriod, careerViewModelStartDateGetValue);
        }
        if ((dirtyFlags & 0x28fL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityCareerSaveBtn, careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseActivityCareerSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityCareerSaveBtnAndroidDrawableBasicGrayBtnLayout);
            this.activityCareerSaveBtn.setClickable(careerViewModelCompanyIsValidCareerViewModelPositionIsValidBooleanFalseCareerViewModelStartDateIsValidBooleanFalseCareerViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse);
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





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityCareerEtPosition.getResources().getString(R.string.register_input_position_desc));
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





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityCareerEtCompanyDesc.getResources().getString(R.string.register_input_company_desc));
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): careerViewModel.positionIsValid
        flag 1 (0x2L): careerViewModel.companyIsValid
        flag 2 (0x3L): careerViewModel.startDateIsValid
        flag 3 (0x4L): careerViewModel.endDateIsValid
        flag 4 (0x5L): careerViewModel.startDate
        flag 5 (0x6L): careerViewModel.company
        flag 6 (0x7L): careerViewModel.position
        flag 7 (0x8L): careerViewModel
        flag 8 (0x9L): editTextViewModel
        flag 9 (0xaL): null
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? true : false
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false ? true : false
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.companyIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.positionIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(careerViewModel.endDateIsValid.getValue()) : false
    flag mapping end*/
    //end
}