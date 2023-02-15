package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileEditBindingImpl extends FragmentProfileEditBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_edit_profile_ivProfile, 5);
        sViewsWithIds.put(R.id.activity_edit_profile_ivplus, 6);
        sViewsWithIds.put(R.id.activity_edit_profile_tv_nickTitle, 7);
        sViewsWithIds.put(R.id.activity_nickname_state, 8);
        sViewsWithIds.put(R.id.activity_edit_profile_tv_teamTitle, 9);
        sViewsWithIds.put(R.id.activity_team_state, 10);
        sViewsWithIds.put(R.id.activity_edit_profile_tv_emailTitle, 11);
        sViewsWithIds.put(R.id.activity_email_state, 12);
        sViewsWithIds.put(R.id.activity_edit_profile_tv_introTitle, 13);
        sViewsWithIds.put(R.id.activity_education_save_btn, 14);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityEditProfileEtEmailandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of profileViewModel.email.getValue()
            //         is profileViewModel.email.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEditProfileEtEmail);
            // localize variables for thread safety
            // profileViewModel.email != null
            boolean profileViewModelEmailJavaLangObjectNull = false;
            // profileViewModel.email.getValue()
            java.lang.String profileViewModelEmailGetValue = null;
            // profileViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
            // profileViewModel != null
            boolean profileViewModelJavaLangObjectNull = false;
            // profileViewModel.email
            androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelEmail = null;



            profileViewModelJavaLangObjectNull = (profileViewModel) != (null);
            if (profileViewModelJavaLangObjectNull) {


                profileViewModelEmail = profileViewModel.getEmail();

                profileViewModelEmailJavaLangObjectNull = (profileViewModelEmail) != (null);
                if (profileViewModelEmailJavaLangObjectNull) {




                    profileViewModelEmail.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityEditProfileEtIntroandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of profileViewModel.intro.getValue()
            //         is profileViewModel.intro.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEditProfileEtIntro);
            // localize variables for thread safety
            // profileViewModel.intro != null
            boolean profileViewModelIntroJavaLangObjectNull = false;
            // profileViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
            // profileViewModel != null
            boolean profileViewModelJavaLangObjectNull = false;
            // profileViewModel.intro
            androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelIntro = null;
            // profileViewModel.intro.getValue()
            java.lang.String profileViewModelIntroGetValue = null;



            profileViewModelJavaLangObjectNull = (profileViewModel) != (null);
            if (profileViewModelJavaLangObjectNull) {


                profileViewModelIntro = profileViewModel.getIntro();

                profileViewModelIntroJavaLangObjectNull = (profileViewModelIntro) != (null);
                if (profileViewModelIntroJavaLangObjectNull) {




                    profileViewModelIntro.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityEditProfileEtNickandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of profileViewModel.nickName.getValue()
            //         is profileViewModel.nickName.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEditProfileEtNick);
            // localize variables for thread safety
            // profileViewModel.nickName
            androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelNickName = null;
            // profileViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
            // profileViewModel.nickName != null
            boolean profileViewModelNickNameJavaLangObjectNull = false;
            // profileViewModel != null
            boolean profileViewModelJavaLangObjectNull = false;
            // profileViewModel.nickName.getValue()
            java.lang.String profileViewModelNickNameGetValue = null;



            profileViewModelJavaLangObjectNull = (profileViewModel) != (null);
            if (profileViewModelJavaLangObjectNull) {


                profileViewModelNickName = profileViewModel.getNickName();

                profileViewModelNickNameJavaLangObjectNull = (profileViewModelNickName) != (null);
                if (profileViewModelNickNameJavaLangObjectNull) {




                    profileViewModelNickName.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener activityEditProfileEtTeamandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of profileViewModel.belong.getValue()
            //         is profileViewModel.belong.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityEditProfileEtTeam);
            // localize variables for thread safety
            // profileViewModel.belong.getValue()
            java.lang.String profileViewModelBelongGetValue = null;
            // profileViewModel.belong
            androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelBelong = null;
            // profileViewModel
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
            // profileViewModel != null
            boolean profileViewModelJavaLangObjectNull = false;
            // profileViewModel.belong != null
            boolean profileViewModelBelongJavaLangObjectNull = false;



            profileViewModelJavaLangObjectNull = (profileViewModel) != (null);
            if (profileViewModelJavaLangObjectNull) {


                profileViewModelBelong = profileViewModel.getBelong();

                profileViewModelBelongJavaLangObjectNull = (profileViewModelBelong) != (null);
                if (profileViewModelBelongJavaLangObjectNull) {




                    profileViewModelBelong.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentProfileEditBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentProfileEditBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatButton) bindings[14]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[10]
            );
        this.activityEditProfileEtEmail.setTag(null);
        this.activityEditProfileEtIntro.setTag(null);
        this.activityEditProfileEtNick.setTag(null);
        this.activityEditProfileEtTeam.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
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
        if (BR.editViewModel == variableId) {
            setEditViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel) variable);
        }
        else if (BR.profileViewModel == variableId) {
            setProfileViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEditViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel EditViewModel) {
        this.mEditViewModel = EditViewModel;
    }
    public void setProfileViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel ProfileViewModel) {
        this.mProfileViewModel = ProfileViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.profileViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeProfileViewModelNickName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeProfileViewModelBelong((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeProfileViewModelIntro((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 3 :
                return onChangeProfileViewModelEmail((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeProfileViewModelNickName(androidx.lifecycle.MutableLiveData<java.lang.String> ProfileViewModelNickName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeProfileViewModelBelong(androidx.lifecycle.MutableLiveData<java.lang.String> ProfileViewModelBelong, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeProfileViewModelIntro(androidx.lifecycle.MutableLiveData<java.lang.String> ProfileViewModelIntro, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeProfileViewModelEmail(androidx.lifecycle.MutableLiveData<java.lang.String> ProfileViewModelEmail, int fieldId) {
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
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
        java.lang.String profileViewModelNickNameGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelNickName = null;
        java.lang.String profileViewModelEmailGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelBelong = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelIntro = null;
        java.lang.String profileViewModelIntroGetValue = null;
        java.lang.String profileViewModelBelongGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> profileViewModelEmail = null;

        if ((dirtyFlags & 0x6fL) != 0) {


            if ((dirtyFlags & 0x61L) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.nickName
                        profileViewModelNickName = profileViewModel.getNickName();
                    }
                    updateLiveDataRegistration(0, profileViewModelNickName);


                    if (profileViewModelNickName != null) {
                        // read profileViewModel.nickName.getValue()
                        profileViewModelNickNameGetValue = profileViewModelNickName.getValue();
                    }
            }
            if ((dirtyFlags & 0x62L) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.belong
                        profileViewModelBelong = profileViewModel.getBelong();
                    }
                    updateLiveDataRegistration(1, profileViewModelBelong);


                    if (profileViewModelBelong != null) {
                        // read profileViewModel.belong.getValue()
                        profileViewModelBelongGetValue = profileViewModelBelong.getValue();
                    }
            }
            if ((dirtyFlags & 0x64L) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.intro
                        profileViewModelIntro = profileViewModel.getIntro();
                    }
                    updateLiveDataRegistration(2, profileViewModelIntro);


                    if (profileViewModelIntro != null) {
                        // read profileViewModel.intro.getValue()
                        profileViewModelIntroGetValue = profileViewModelIntro.getValue();
                    }
            }
            if ((dirtyFlags & 0x68L) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.email
                        profileViewModelEmail = profileViewModel.getEmail();
                    }
                    updateLiveDataRegistration(3, profileViewModelEmail);


                    if (profileViewModelEmail != null) {
                        // read profileViewModel.email.getValue()
                        profileViewModelEmailGetValue = profileViewModelEmail.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x68L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEditProfileEtEmail, profileViewModelEmailGetValue);
        }
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEditProfileEtEmail, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEditProfileEtEmailandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEditProfileEtIntro, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEditProfileEtIntroandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEditProfileEtNick, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEditProfileEtNickandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityEditProfileEtTeam, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityEditProfileEtTeamandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x64L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEditProfileEtIntro, profileViewModelIntroGetValue);
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEditProfileEtNick, profileViewModelNickNameGetValue);
        }
        if ((dirtyFlags & 0x62L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityEditProfileEtTeam, profileViewModelBelongGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): profileViewModel.nickName
        flag 1 (0x2L): profileViewModel.belong
        flag 2 (0x3L): profileViewModel.intro
        flag 3 (0x4L): profileViewModel.email
        flag 4 (0x5L): editViewModel
        flag 5 (0x6L): profileViewModel
        flag 6 (0x7L): null
    flag mapping end*/
    //end
}