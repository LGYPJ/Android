package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileEducationBindingImpl extends FragmentProfileEducationBinding implements com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_education_info, 5);
        sViewsWithIds.put(R.id.activity_education_tv_institutionTitle, 6);
        sViewsWithIds.put(R.id.caution_input_institution_tv, 7);
        sViewsWithIds.put(R.id.activity_education_tv_majorTitle, 8);
        sViewsWithIds.put(R.id.caution_input_major_tv, 9);
        sViewsWithIds.put(R.id.activity_education_tv_periodTitle, 10);
        sViewsWithIds.put(R.id.activity_education_tv_period, 11);
        sViewsWithIds.put(R.id.activity_education_et_endPeriod, 12);
        sViewsWithIds.put(R.id.activity_education_checkbox_rl, 13);
        sViewsWithIds.put(R.id.activity_education_checkbox, 14);
        sViewsWithIds.put(R.id.activity_education_checkbox_desc, 15);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback15;
    @Nullable
    private final com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener mCallback16;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityEducationEtInstitutionDescandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of educationViewModel.institution.getValue()
            //         is educationViewModel.institution.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEducationEtInstitutionDesc);
            // localize variables for thread safety
            // educationViewModel.institution
            androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelInstitution = null;
            // educationViewModel.institution != null
            boolean educationViewModelInstitutionJavaLangObjectNull = false;
            // educationViewModel.institution.getValue()
            java.lang.String educationViewModelInstitutionGetValue = null;
            // educationViewModel != null
            boolean educationViewModelJavaLangObjectNull = false;
            // educationViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel educationViewModel = mEducationViewModel;



            educationViewModelJavaLangObjectNull = (educationViewModel) != (null);
            if (educationViewModelJavaLangObjectNull) {


                educationViewModelInstitution = educationViewModel.getInstitution();

                educationViewModelInstitutionJavaLangObjectNull = (educationViewModelInstitution) != (null);
                if (educationViewModelInstitutionJavaLangObjectNull) {




                    educationViewModelInstitution.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityEducationEtMajorDescandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of educationViewModel.major.getValue()
            //         is educationViewModel.major.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEducationEtMajorDesc);
            // localize variables for thread safety
            // educationViewModel.major.getValue()
            java.lang.String educationViewModelMajorGetValue = null;
            // educationViewModel.major != null
            boolean educationViewModelMajorJavaLangObjectNull = false;
            // educationViewModel != null
            boolean educationViewModelJavaLangObjectNull = false;
            // educationViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel educationViewModel = mEducationViewModel;
            // educationViewModel.major
            androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelMajor = null;



            educationViewModelJavaLangObjectNull = (educationViewModel) != (null);
            if (educationViewModelJavaLangObjectNull) {


                educationViewModelMajor = educationViewModel.getMajor();

                educationViewModelMajorJavaLangObjectNull = (educationViewModelMajor) != (null);
                if (educationViewModelMajorJavaLangObjectNull) {




                    educationViewModelMajor.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityEducationEtStartPeriodandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of educationViewModel.startDate.getValue()
            //         is educationViewModel.startDate.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEducationEtStartPeriod);
            // localize variables for thread safety
            // educationViewModel.startDate
            androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelStartDate = null;
            // educationViewModel.startDate.getValue()
            java.lang.String educationViewModelStartDateGetValue = null;
            // educationViewModel != null
            boolean educationViewModelJavaLangObjectNull = false;
            // educationViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel educationViewModel = mEducationViewModel;
            // educationViewModel.startDate != null
            boolean educationViewModelStartDateJavaLangObjectNull = false;



            educationViewModelJavaLangObjectNull = (educationViewModel) != (null);
            if (educationViewModelJavaLangObjectNull) {


                educationViewModelStartDate = educationViewModel.getStartDate();

                educationViewModelStartDateJavaLangObjectNull = (educationViewModelStartDate) != (null);
                if (educationViewModelStartDateJavaLangObjectNull) {




                    educationViewModelStartDate.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentProfileEducationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentProfileEducationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 7
            , (android.widget.CheckBox) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.RelativeLayout) bindings[13]
            , (android.widget.TextView) bindings[12]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.appcompat.widget.AppCompatButton) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            );
        this.activityEducationEtInstitutionDesc.setTag(null);
        this.activityEducationEtMajorDesc.setTag(null);
        this.activityEducationEtStartPeriod.setTag(null);
        this.activityEducationSaveBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback15 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 1);
        mCallback16 = new com.garamgaebi.garamgaebi.generated.callback.OnFocusLostListener(this, 2);
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
        if (BR.educationViewModel == variableId) {
            setEducationViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel) variable);
        }
        else if (BR.editTextViewModel == variableId) {
            setEditTextViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEducationViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel EducationViewModel) {
        this.mEducationViewModel = EducationViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x80L;
        }
        notifyPropertyChanged(BR.educationViewModel);
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
                return onChangeEducationViewModelMajor((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeEducationViewModelInstitution((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeEducationViewModelMajorIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeEducationViewModelEndDateIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 4 :
                return onChangeEducationViewModelStartDateIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 5 :
                return onChangeEducationViewModelStartDate((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 6 :
                return onChangeEducationViewModelInstitutionIsValid((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeEducationViewModelMajor(androidx.lifecycle.MutableLiveData<java.lang.String> EducationViewModelMajor, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelInstitution(androidx.lifecycle.MutableLiveData<java.lang.String> EducationViewModelInstitution, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelMajorIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> EducationViewModelMajorIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelEndDateIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> EducationViewModelEndDateIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelStartDateIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> EducationViewModelStartDateIsValid, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelStartDate(androidx.lifecycle.MutableLiveData<java.lang.String> EducationViewModelStartDate, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeEducationViewModelInstitutionIsValid(androidx.lifecycle.MutableLiveData<java.lang.Boolean> EducationViewModelInstitutionIsValid, int fieldId) {
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
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel educationViewModel = mEducationViewModel;
        boolean androidxDatabindingViewDataBindingSafeUnboxEducationViewModelMajorIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelMajor = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelInstitution = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> educationViewModelMajorIsValid = null;
        java.lang.Boolean educationViewModelStartDateIsValidGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> educationViewModelEndDateIsValid = null;
        boolean educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalse = false;
        boolean educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalse = false;
        java.lang.Boolean educationViewModelInstitutionIsValidGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxEducationViewModelStartDateIsValidGetValue = false;
        android.graphics.drawable.Drawable educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseActivityEducationSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityEducationSaveBtnAndroidDrawableBasicGrayBtnLayout = null;
        java.lang.Boolean educationViewModelMajorIsValidGetValue = null;
        boolean educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> educationViewModelStartDateIsValid = null;
        boolean educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> educationViewModelStartDate = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxEducationViewModelInstitutionIsValidGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxEducationViewModelEndDateIsValidGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> educationViewModelInstitutionIsValid = null;
        java.lang.String educationViewModelInstitutionGetValue = null;
        java.lang.String educationViewModelStartDateGetValue = null;
        java.lang.String educationViewModelMajorGetValue = null;
        java.lang.Boolean educationViewModelEndDateIsValidGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;

        if ((dirtyFlags & 0x2ffL) != 0) {


            if ((dirtyFlags & 0x281L) != 0) {

                    if (educationViewModel != null) {
                        // read educationViewModel.major
                        educationViewModelMajor = educationViewModel.getMajor();
                    }
                    updateLiveDataRegistration(0, educationViewModelMajor);


                    if (educationViewModelMajor != null) {
                        // read educationViewModel.major.getValue()
                        educationViewModelMajorGetValue = educationViewModelMajor.getValue();
                    }
            }
            if ((dirtyFlags & 0x282L) != 0) {

                    if (educationViewModel != null) {
                        // read educationViewModel.institution
                        educationViewModelInstitution = educationViewModel.getInstitution();
                    }
                    updateLiveDataRegistration(1, educationViewModelInstitution);


                    if (educationViewModelInstitution != null) {
                        // read educationViewModel.institution.getValue()
                        educationViewModelInstitutionGetValue = educationViewModelInstitution.getValue();
                    }
            }
            if ((dirtyFlags & 0x2a0L) != 0) {

                    if (educationViewModel != null) {
                        // read educationViewModel.startDate
                        educationViewModelStartDate = educationViewModel.getStartDate();
                    }
                    updateLiveDataRegistration(5, educationViewModelStartDate);


                    if (educationViewModelStartDate != null) {
                        // read educationViewModel.startDate.getValue()
                        educationViewModelStartDateGetValue = educationViewModelStartDate.getValue();
                    }
            }
            if ((dirtyFlags & 0x2dcL) != 0) {

                    if (educationViewModel != null) {
                        // read educationViewModel.institutionIsValid
                        educationViewModelInstitutionIsValid = educationViewModel.getInstitutionIsValid();
                    }
                    updateLiveDataRegistration(6, educationViewModelInstitutionIsValid);


                    if (educationViewModelInstitutionIsValid != null) {
                        // read educationViewModel.institutionIsValid.getValue()
                        educationViewModelInstitutionIsValidGetValue = educationViewModelInstitutionIsValid.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxEducationViewModelInstitutionIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(educationViewModelInstitutionIsValidGetValue);
                if((dirtyFlags & 0x2dcL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxEducationViewModelInstitutionIsValidGetValue) {
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                    }
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x800L) != 0) {

                if (educationViewModel != null) {
                    // read educationViewModel.majorIsValid
                    educationViewModelMajorIsValid = educationViewModel.getMajorIsValid();
                }
                updateLiveDataRegistration(2, educationViewModelMajorIsValid);


                if (educationViewModelMajorIsValid != null) {
                    // read educationViewModel.majorIsValid.getValue()
                    educationViewModelMajorIsValidGetValue = educationViewModelMajorIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxEducationViewModelMajorIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(educationViewModelMajorIsValidGetValue);
        }

        if ((dirtyFlags & 0x2dcL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false
                educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalse = ((androidxDatabindingViewDataBindingSafeUnboxEducationViewModelInstitutionIsValidGetValue) ? (androidxDatabindingViewDataBindingSafeUnboxEducationViewModelMajorIsValidGetValue) : (false));
            if((dirtyFlags & 0x2dcL) != 0) {
                if(educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalse) {
                        dirtyFlags |= 0x2000L;
                }
                else {
                        dirtyFlags |= 0x1000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000L) != 0) {

                if (educationViewModel != null) {
                    // read educationViewModel.startDateIsValid
                    educationViewModelStartDateIsValid = educationViewModel.getStartDateIsValid();
                }
                updateLiveDataRegistration(4, educationViewModelStartDateIsValid);


                if (educationViewModelStartDateIsValid != null) {
                    // read educationViewModel.startDateIsValid.getValue()
                    educationViewModelStartDateIsValidGetValue = educationViewModelStartDateIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxEducationViewModelStartDateIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(educationViewModelStartDateIsValidGetValue);
        }

        if ((dirtyFlags & 0x2dcL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false
                educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalse = ((educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxEducationViewModelStartDateIsValidGetValue) : (false));
            if((dirtyFlags & 0x2dcL) != 0) {
                if(educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalse) {
                        dirtyFlags |= 0x20000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x20000L) != 0) {

                if (educationViewModel != null) {
                    // read educationViewModel.endDateIsValid
                    educationViewModelEndDateIsValid = educationViewModel.getEndDateIsValid();
                }
                updateLiveDataRegistration(3, educationViewModelEndDateIsValid);


                if (educationViewModelEndDateIsValid != null) {
                    // read educationViewModel.endDateIsValid.getValue()
                    educationViewModelEndDateIsValidGetValue = educationViewModelEndDateIsValid.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue())
                androidxDatabindingViewDataBindingSafeUnboxEducationViewModelEndDateIsValidGetValue = androidx.databinding.ViewDataBinding.safeUnbox(educationViewModelEndDateIsValidGetValue);
        }

        if ((dirtyFlags & 0x2dcL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false
                educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalse = ((educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalse) ? (androidxDatabindingViewDataBindingSafeUnboxEducationViewModelEndDateIsValidGetValue) : (false));
            if((dirtyFlags & 0x2dcL) != 0) {
                if(educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalse) {
                        dirtyFlags |= 0x8000L;
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x4000L;
                        dirtyFlags |= 0x40000L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
                educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseActivityEducationSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityEducationSaveBtnAndroidDrawableBasicGrayBtnLayout = ((educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalse) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityEducationSaveBtn.getContext(), R.drawable.basic_blue_btn_layout)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(activityEducationSaveBtn.getContext(), R.drawable.basic_gray_btn_layout)));
                // read androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? true : false
                educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse = ((educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalse) ? (true) : (false));
        }
        // batch finished
        if ((dirtyFlags & 0x282L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEducationEtInstitutionDesc, educationViewModelInstitutionGetValue);
        }
        if ((dirtyFlags & 0x200L) != 0) {
            // api target 1

            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityEducationEtInstitutionDesc, mCallback15);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEducationEtInstitutionDesc, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEducationEtInstitutionDescandroidTextAttrChanged);
            com.garamgaebi.garamgaebi.garamgaebi.common.BindingAdapters.onFocusLost(this.activityEducationEtMajorDesc, mCallback16);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEducationEtMajorDesc, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEducationEtMajorDescandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEducationEtStartPeriod, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEducationEtStartPeriodandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x281L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEducationEtMajorDesc, educationViewModelMajorGetValue);
        }
        if ((dirtyFlags & 0x2a0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEducationEtStartPeriod, educationViewModelStartDateGetValue);
        }
        if ((dirtyFlags & 0x2dcL) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setBackground(this.activityEducationSaveBtn, educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseActivityEducationSaveBtnAndroidDrawableBasicBlueBtnLayoutActivityEducationSaveBtnAndroidDrawableBasicGrayBtnLayout);
            this.activityEducationSaveBtn.setClickable(educationViewModelInstitutionIsValidEducationViewModelMajorIsValidBooleanFalseEducationViewModelStartDateIsValidBooleanFalseEducationViewModelEndDateIsValidBooleanFalseBooleanTrueBooleanFalse);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnFocusLost(int sourceId , android.widget.EditText callbackArg_0, boolean callbackArg_1) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // editTextViewModel != null
                boolean editTextViewModelJavaLangObjectNull = false;
                // editTextViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



                editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
                if (editTextViewModelJavaLangObjectNull) {





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityEducationEtInstitutionDesc.getResources().getString(R.string.register_input_institution_desc));
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // editTextViewModel != null
                boolean editTextViewModelJavaLangObjectNull = false;
                // editTextViewModel
                com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel editTextViewModel = mEditTextViewModel;



                editTextViewModelJavaLangObjectNull = (editTextViewModel) != (null);
                if (editTextViewModelJavaLangObjectNull) {





                    editTextViewModel.setBackgroundEditTextOnFocus(callbackArg_0, callbackArg_1, activityEducationEtMajorDesc.getResources().getString(R.string.register_input_major));
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): educationViewModel.major
        flag 1 (0x2L): educationViewModel.institution
        flag 2 (0x3L): educationViewModel.majorIsValid
        flag 3 (0x4L): educationViewModel.endDateIsValid
        flag 4 (0x5L): educationViewModel.startDateIsValid
        flag 5 (0x6L): educationViewModel.startDate
        flag 6 (0x7L): educationViewModel.institutionIsValid
        flag 7 (0x8L): educationViewModel
        flag 8 (0x9L): editTextViewModel
        flag 9 (0xaL): null
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false
        flag 13 (0xeL): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false
        flag 14 (0xfL): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 15 (0x10L): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? @android:drawable/basic_blue_btn_layout : @android:drawable/basic_gray_btn_layout
        flag 16 (0x11L): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false
        flag 17 (0x12L): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false
        flag 18 (0x13L): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? true : false
        flag 19 (0x14L): androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.institutionIsValid.getValue()) ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.majorIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.startDateIsValid.getValue()) : false ? androidx.databinding.ViewDataBinding.safeUnbox(educationViewModel.endDateIsValid.getValue()) : false ? true : false
    flag mapping end*/
    //end
}