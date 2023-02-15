package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMyprofileBindingImpl extends FragmentMyprofileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_myProfile_clContainer, 7);
        sViewsWithIds.put(R.id.activity_myProfile_tvTitle, 8);
        sViewsWithIds.put(R.id.activity_myProfile_ivCs, 9);
        sViewsWithIds.put(R.id.activity_myProfile_sv_main, 10);
        sViewsWithIds.put(R.id.activity_myProfile_cl_main, 11);
        sViewsWithIds.put(R.id.activity_myProfile_ivProfile, 12);
        sViewsWithIds.put(R.id.activity_myProfile_tvUsername, 13);
        sViewsWithIds.put(R.id.activity_myProfile_tvSchool, 14);
        sViewsWithIds.put(R.id.activity_myProfile_tvEmail, 15);
        sViewsWithIds.put(R.id.activity_myProfile_tv_intro, 16);
        sViewsWithIds.put(R.id.activity_myProfile_btnEditProfile, 17);
        sViewsWithIds.put(R.id.activity_myProfile_cl_snsTitle, 18);
        sViewsWithIds.put(R.id.activity_myProfile_tv_snsTitle, 19);
        sViewsWithIds.put(R.id.activity_myProfile_cl_snsDesc, 20);
        sViewsWithIds.put(R.id.activity_myProfile_RV_sns, 21);
        sViewsWithIds.put(R.id.activity_myProfile_cl_careerTitle, 22);
        sViewsWithIds.put(R.id.activity_myProfile_tv_careerTitle, 23);
        sViewsWithIds.put(R.id.activity_myProfile_cl_careerDesc, 24);
        sViewsWithIds.put(R.id.activity_myProfile_RV_career, 25);
        sViewsWithIds.put(R.id.activity_myProfile_cl_eduTitle, 26);
        sViewsWithIds.put(R.id.activity_myProfile_tv_eduTitle, 27);
        sViewsWithIds.put(R.id.activity_myProfile_cl_eduDesc, 28);
        sViewsWithIds.put(R.id.activity_myProfile_RV_edu, 29);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMyprofileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }
    private FragmentMyprofileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (androidx.appcompat.widget.AppCompatButton) bindings[4]
            , (androidx.appcompat.widget.AppCompatButton) bindings[17]
            , (androidx.appcompat.widget.AppCompatButton) bindings[6]
            , (androidx.appcompat.widget.AppCompatButton) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[22]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[28]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[26]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[20]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[18]
            , (android.widget.ImageView) bindings[9]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[12]
            , (androidx.recyclerview.widget.RecyclerView) bindings[25]
            , (androidx.recyclerview.widget.RecyclerView) bindings[29]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (android.widget.ScrollView) bindings[10]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[13]
            );
        this.activityMyProfileBtnCareerAdd.setTag(null);
        this.activityMyProfileBtnEduAdd.setTag(null);
        this.activityMyProfileBtnSnsAdd.setTag(null);
        this.activityMyProfileTvCareerDesc.setTag(null);
        this.activityMyProfileTvEduDesc.setTag(null);
        this.activityMyProfileTvSnsDesc.setTag(null);
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
        if (BR.profileViewModel == variableId) {
            setProfileViewModel((com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel) variable);
        }
        else if (BR.profileFragment == variableId) {
            setProfileFragment((com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.MyProfileFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setProfileViewModel(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel ProfileViewModel) {
        this.mProfileViewModel = ProfileViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.profileViewModel);
        super.requestRebind();
    }
    public void setProfileFragment(@Nullable com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.MyProfileFragment ProfileFragment) {
        this.mProfileFragment = ProfileFragment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeProfileViewModelSnsInfoArray((androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.SNSData>>) object, fieldId);
            case 1 :
                return onChangeProfileViewModelEducationInfoArray((androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.EducationData>>) object, fieldId);
            case 2 :
                return onChangeProfileViewModelCareerInfoArray((androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.CareerData>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeProfileViewModelSnsInfoArray(androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.SNSData>> ProfileViewModelSnsInfoArray, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeProfileViewModelEducationInfoArray(androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.EducationData>> ProfileViewModelEducationInfoArray, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeProfileViewModelCareerInfoArray(androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.CareerData>> ProfileViewModelCareerInfoArray, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.SNSData>> profileViewModelSnsInfoArray = null;
        java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.SNSData> profileViewModelSnsInfoArrayGetValue = null;
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel profileViewModel = mProfileViewModel;
        boolean profileViewModelCareerInfoArrayIsEmpty = false;
        int profileViewModelCareerInfoArrayIsEmptyViewVISIBLEViewGONE = 0;
        boolean profileViewModelEducationInfoArrayIsEmpty = false;
        java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.EducationData> profileViewModelEducationInfoArrayGetValue = null;
        int profileViewModelSnsInfoArrayIsEmptyViewVISIBLEViewGONE = 0;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.EducationData>> profileViewModelEducationInfoArray = null;
        androidx.lifecycle.LiveData<java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.CareerData>> profileViewModelCareerInfoArray = null;
        boolean profileViewModelSnsInfoArrayIsEmpty = false;
        java.util.ArrayList<com.garamgaebi.garamgaebi.garamgaebi.model.CareerData> profileViewModelCareerInfoArrayGetValue = null;
        int profileViewModelEducationInfoArrayIsEmptyViewVISIBLEViewGONE = 0;

        if ((dirtyFlags & 0x2fL) != 0) {


            if ((dirtyFlags & 0x29L) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.snsInfoArray
                        profileViewModelSnsInfoArray = profileViewModel.getSnsInfoArray();
                    }
                    updateLiveDataRegistration(0, profileViewModelSnsInfoArray);


                    if (profileViewModelSnsInfoArray != null) {
                        // read profileViewModel.snsInfoArray.getValue()
                        profileViewModelSnsInfoArrayGetValue = profileViewModelSnsInfoArray.getValue();
                    }


                    if (profileViewModelSnsInfoArrayGetValue != null) {
                        // read profileViewModel.snsInfoArray.getValue().isEmpty
                        profileViewModelSnsInfoArrayIsEmpty = profileViewModelSnsInfoArrayGetValue.isEmpty();
                    }
                if((dirtyFlags & 0x29L) != 0) {
                    if(profileViewModelSnsInfoArrayIsEmpty) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read profileViewModel.snsInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
                    profileViewModelSnsInfoArrayIsEmptyViewVISIBLEViewGONE = ((profileViewModelSnsInfoArrayIsEmpty) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x2aL) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.educationInfoArray
                        profileViewModelEducationInfoArray = profileViewModel.getEducationInfoArray();
                    }
                    updateLiveDataRegistration(1, profileViewModelEducationInfoArray);


                    if (profileViewModelEducationInfoArray != null) {
                        // read profileViewModel.educationInfoArray.getValue()
                        profileViewModelEducationInfoArrayGetValue = profileViewModelEducationInfoArray.getValue();
                    }


                    if (profileViewModelEducationInfoArrayGetValue != null) {
                        // read profileViewModel.educationInfoArray.getValue().isEmpty
                        profileViewModelEducationInfoArrayIsEmpty = profileViewModelEducationInfoArrayGetValue.isEmpty();
                    }
                if((dirtyFlags & 0x2aL) != 0) {
                    if(profileViewModelEducationInfoArrayIsEmpty) {
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x400L;
                    }
                }


                    // read profileViewModel.educationInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
                    profileViewModelEducationInfoArrayIsEmptyViewVISIBLEViewGONE = ((profileViewModelEducationInfoArrayIsEmpty) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x2cL) != 0) {

                    if (profileViewModel != null) {
                        // read profileViewModel.careerInfoArray
                        profileViewModelCareerInfoArray = profileViewModel.getCareerInfoArray();
                    }
                    updateLiveDataRegistration(2, profileViewModelCareerInfoArray);


                    if (profileViewModelCareerInfoArray != null) {
                        // read profileViewModel.careerInfoArray.getValue()
                        profileViewModelCareerInfoArrayGetValue = profileViewModelCareerInfoArray.getValue();
                    }


                    if (profileViewModelCareerInfoArrayGetValue != null) {
                        // read profileViewModel.careerInfoArray.getValue().isEmpty
                        profileViewModelCareerInfoArrayIsEmpty = profileViewModelCareerInfoArrayGetValue.isEmpty();
                    }
                if((dirtyFlags & 0x2cL) != 0) {
                    if(profileViewModelCareerInfoArrayIsEmpty) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read profileViewModel.careerInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
                    profileViewModelCareerInfoArrayIsEmptyViewVISIBLEViewGONE = ((profileViewModelCareerInfoArrayIsEmpty) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyProfileBtnCareerAdd, ((activityMyProfileBtnCareerAdd.getResources().getString(R.string.career)) + (' ')) + (activityMyProfileBtnCareerAdd.getResources().getString(R.string.add)));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyProfileBtnEduAdd, ((activityMyProfileBtnEduAdd.getResources().getString(R.string.education)) + (' ')) + (activityMyProfileBtnEduAdd.getResources().getString(R.string.add)));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyProfileBtnSnsAdd, ((activityMyProfileBtnSnsAdd.getResources().getString(R.string.sns)) + (' ')) + (activityMyProfileBtnSnsAdd.getResources().getString(R.string.add)));
        }
        if ((dirtyFlags & 0x2cL) != 0) {
            // api target 1

            this.activityMyProfileTvCareerDesc.setVisibility(profileViewModelCareerInfoArrayIsEmptyViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x2aL) != 0) {
            // api target 1

            this.activityMyProfileTvEduDesc.setVisibility(profileViewModelEducationInfoArrayIsEmptyViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x29L) != 0) {
            // api target 1

            this.activityMyProfileTvSnsDesc.setVisibility(profileViewModelSnsInfoArrayIsEmptyViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): profileViewModel.snsInfoArray
        flag 1 (0x2L): profileViewModel.educationInfoArray
        flag 2 (0x3L): profileViewModel.careerInfoArray
        flag 3 (0x4L): profileViewModel
        flag 4 (0x5L): profileFragment
        flag 5 (0x6L): null
        flag 6 (0x7L): profileViewModel.careerInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
        flag 7 (0x8L): profileViewModel.careerInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
        flag 8 (0x9L): profileViewModel.snsInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
        flag 9 (0xaL): profileViewModel.snsInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
        flag 10 (0xbL): profileViewModel.educationInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
        flag 11 (0xcL): profileViewModel.educationInfoArray.getValue().isEmpty ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}