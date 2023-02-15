package com.garamgaebi.garamgaebi;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.garamgaebi.garamgaebi.databinding.FragmentCancelBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentMyprofileBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingChargedApplyBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingFreeApplyBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerEditBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEditBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationEditBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsEditBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterAuthenticationBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCareerBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEducationBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEmailBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterNicknameBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentSeminarChargedApplyBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentSeminarFreeApplyBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentServicecenterBindingImpl;
import com.garamgaebi.garamgaebi.databinding.FragmentWithdrawalBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileCareerBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileEduBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileSnsBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemSomeoneprofileCareerBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemSomeoneprofileEduBindingImpl;
import com.garamgaebi.garamgaebi.databinding.ItemSomeoneprofileSnsBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTCANCEL = 1;

  private static final int LAYOUT_FRAGMENTMYPROFILE = 2;

  private static final int LAYOUT_FRAGMENTNETWORKINGCHARGEDAPPLY = 3;

  private static final int LAYOUT_FRAGMENTNETWORKINGFREEAPPLY = 4;

  private static final int LAYOUT_FRAGMENTPROFILECAREER = 5;

  private static final int LAYOUT_FRAGMENTPROFILECAREEREDIT = 6;

  private static final int LAYOUT_FRAGMENTPROFILEEDIT = 7;

  private static final int LAYOUT_FRAGMENTPROFILEEDUCATION = 8;

  private static final int LAYOUT_FRAGMENTPROFILEEDUCATIONEDIT = 9;

  private static final int LAYOUT_FRAGMENTPROFILESNS = 10;

  private static final int LAYOUT_FRAGMENTPROFILESNSEDIT = 11;

  private static final int LAYOUT_FRAGMENTREGISTERAUTHENTICATION = 12;

  private static final int LAYOUT_FRAGMENTREGISTERCAREER = 13;

  private static final int LAYOUT_FRAGMENTREGISTEREDUCATION = 14;

  private static final int LAYOUT_FRAGMENTREGISTEREMAIL = 15;

  private static final int LAYOUT_FRAGMENTREGISTERNICKNAME = 16;

  private static final int LAYOUT_FRAGMENTSEMINARCHARGEDAPPLY = 17;

  private static final int LAYOUT_FRAGMENTSEMINARFREEAPPLY = 18;

  private static final int LAYOUT_FRAGMENTSERVICECENTER = 19;

  private static final int LAYOUT_FRAGMENTWITHDRAWAL = 20;

  private static final int LAYOUT_ITEMMYPROFILECAREER = 21;

  private static final int LAYOUT_ITEMMYPROFILEEDU = 22;

  private static final int LAYOUT_ITEMMYPROFILESNS = 23;

  private static final int LAYOUT_ITEMSOMEONEPROFILECAREER = 24;

  private static final int LAYOUT_ITEMSOMEONEPROFILEEDU = 25;

  private static final int LAYOUT_ITEMSOMEONEPROFILESNS = 26;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(26);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_cancel, LAYOUT_FRAGMENTCANCEL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_myprofile, LAYOUT_FRAGMENTMYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_networking_charged_apply, LAYOUT_FRAGMENTNETWORKINGCHARGEDAPPLY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_networking_free_apply, LAYOUT_FRAGMENTNETWORKINGFREEAPPLY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_career, LAYOUT_FRAGMENTPROFILECAREER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_career_edit, LAYOUT_FRAGMENTPROFILECAREEREDIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_edit, LAYOUT_FRAGMENTPROFILEEDIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_education, LAYOUT_FRAGMENTPROFILEEDUCATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_education_edit, LAYOUT_FRAGMENTPROFILEEDUCATIONEDIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_sns, LAYOUT_FRAGMENTPROFILESNS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_sns_edit, LAYOUT_FRAGMENTPROFILESNSEDIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_authentication, LAYOUT_FRAGMENTREGISTERAUTHENTICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_career, LAYOUT_FRAGMENTREGISTERCAREER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_education, LAYOUT_FRAGMENTREGISTEREDUCATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_email, LAYOUT_FRAGMENTREGISTEREMAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_nickname, LAYOUT_FRAGMENTREGISTERNICKNAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_seminar_charged_apply, LAYOUT_FRAGMENTSEMINARCHARGEDAPPLY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_seminar_free_apply, LAYOUT_FRAGMENTSEMINARFREEAPPLY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_servicecenter, LAYOUT_FRAGMENTSERVICECENTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_withdrawal, LAYOUT_FRAGMENTWITHDRAWAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_career, LAYOUT_ITEMMYPROFILECAREER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_edu, LAYOUT_ITEMMYPROFILEEDU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_sns, LAYOUT_ITEMMYPROFILESNS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_career, LAYOUT_ITEMSOMEONEPROFILECAREER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_edu, LAYOUT_ITEMSOMEONEPROFILEEDU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_sns, LAYOUT_ITEMSOMEONEPROFILESNS);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTCANCEL: {
          if ("layout/fragment_cancel_0".equals(tag)) {
            return new FragmentCancelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_cancel is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMYPROFILE: {
          if ("layout/fragment_myprofile_0".equals(tag)) {
            return new FragmentMyprofileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_myprofile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNETWORKINGCHARGEDAPPLY: {
          if ("layout/fragment_networking_charged_apply_0".equals(tag)) {
            return new FragmentNetworkingChargedApplyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_networking_charged_apply is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNETWORKINGFREEAPPLY: {
          if ("layout/fragment_networking_free_apply_0".equals(tag)) {
            return new FragmentNetworkingFreeApplyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_networking_free_apply is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILECAREER: {
          if ("layout/fragment_profile_career_0".equals(tag)) {
            return new FragmentProfileCareerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_career is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILECAREEREDIT: {
          if ("layout/fragment_profile_career_edit_0".equals(tag)) {
            return new FragmentProfileCareerEditBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_career_edit is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEEDIT: {
          if ("layout/fragment_profile_edit_0".equals(tag)) {
            return new FragmentProfileEditBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_edit is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEEDUCATION: {
          if ("layout/fragment_profile_education_0".equals(tag)) {
            return new FragmentProfileEducationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_education is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILEEDUCATIONEDIT: {
          if ("layout/fragment_profile_education_edit_0".equals(tag)) {
            return new FragmentProfileEducationEditBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_education_edit is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILESNS: {
          if ("layout/fragment_profile_sns_0".equals(tag)) {
            return new FragmentProfileSnsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_sns is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILESNSEDIT: {
          if ("layout/fragment_profile_sns_edit_0".equals(tag)) {
            return new FragmentProfileSnsEditBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile_sns_edit is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREGISTERAUTHENTICATION: {
          if ("layout/fragment_register_authentication_0".equals(tag)) {
            return new FragmentRegisterAuthenticationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_register_authentication is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREGISTERCAREER: {
          if ("layout/fragment_register_career_0".equals(tag)) {
            return new FragmentRegisterCareerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_register_career is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREGISTEREDUCATION: {
          if ("layout/fragment_register_education_0".equals(tag)) {
            return new FragmentRegisterEducationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_register_education is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREGISTEREMAIL: {
          if ("layout/fragment_register_email_0".equals(tag)) {
            return new FragmentRegisterEmailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_register_email is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREGISTERNICKNAME: {
          if ("layout/fragment_register_nickname_0".equals(tag)) {
            return new FragmentRegisterNicknameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_register_nickname is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEMINARCHARGEDAPPLY: {
          if ("layout/fragment_seminar_charged_apply_0".equals(tag)) {
            return new FragmentSeminarChargedApplyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_seminar_charged_apply is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEMINARFREEAPPLY: {
          if ("layout/fragment_seminar_free_apply_0".equals(tag)) {
            return new FragmentSeminarFreeApplyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_seminar_free_apply is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSERVICECENTER: {
          if ("layout/fragment_servicecenter_0".equals(tag)) {
            return new FragmentServicecenterBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_servicecenter is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWITHDRAWAL: {
          if ("layout/fragment_withdrawal_0".equals(tag)) {
            return new FragmentWithdrawalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_withdrawal is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMYPROFILECAREER: {
          if ("layout/item_myprofile_career_0".equals(tag)) {
            return new ItemMyprofileCareerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_myprofile_career is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMYPROFILEEDU: {
          if ("layout/item_myprofile_edu_0".equals(tag)) {
            return new ItemMyprofileEduBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_myprofile_edu is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMYPROFILESNS: {
          if ("layout/item_myprofile_sns_0".equals(tag)) {
            return new ItemMyprofileSnsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_myprofile_sns is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSOMEONEPROFILECAREER: {
          if ("layout/item_someoneprofile_career_0".equals(tag)) {
            return new ItemSomeoneprofileCareerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_someoneprofile_career is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSOMEONEPROFILEEDU: {
          if ("layout/item_someoneprofile_edu_0".equals(tag)) {
            return new ItemSomeoneprofileEduBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_someoneprofile_edu is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSOMEONEPROFILESNS: {
          if ("layout/item_someoneprofile_sns_0".equals(tag)) {
            return new ItemSomeoneprofileSnsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_someoneprofile_sns is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(19);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "careerViewModel");
      sKeys.put(2, "common");
      sKeys.put(3, "commonFunction");
      sKeys.put(4, "context");
      sKeys.put(5, "editTextViewModel");
      sKeys.put(6, "editViewModel");
      sKeys.put(7, "educationViewModel");
      sKeys.put(8, "item");
      sKeys.put(9, "profileFragment");
      sKeys.put(10, "profileViewModel");
      sKeys.put(11, "registerViewModel");
      sKeys.put(12, "serviceCenterFragment");
      sKeys.put(13, "serviceCenterViewModel");
      sKeys.put(14, "snsAddFragment");
      sKeys.put(15, "snsEditFragment");
      sKeys.put(16, "snsViewModel");
      sKeys.put(17, "withdrawalFragment");
      sKeys.put(18, "withdrawalViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(26);

    static {
      sKeys.put("layout/fragment_cancel_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_cancel);
      sKeys.put("layout/fragment_myprofile_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_myprofile);
      sKeys.put("layout/fragment_networking_charged_apply_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_networking_charged_apply);
      sKeys.put("layout/fragment_networking_free_apply_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_networking_free_apply);
      sKeys.put("layout/fragment_profile_career_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_career);
      sKeys.put("layout/fragment_profile_career_edit_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_career_edit);
      sKeys.put("layout/fragment_profile_edit_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_edit);
      sKeys.put("layout/fragment_profile_education_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_education);
      sKeys.put("layout/fragment_profile_education_edit_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_education_edit);
      sKeys.put("layout/fragment_profile_sns_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_sns);
      sKeys.put("layout/fragment_profile_sns_edit_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_profile_sns_edit);
      sKeys.put("layout/fragment_register_authentication_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_authentication);
      sKeys.put("layout/fragment_register_career_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_career);
      sKeys.put("layout/fragment_register_education_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_education);
      sKeys.put("layout/fragment_register_email_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_email);
      sKeys.put("layout/fragment_register_nickname_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_register_nickname);
      sKeys.put("layout/fragment_seminar_charged_apply_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_seminar_charged_apply);
      sKeys.put("layout/fragment_seminar_free_apply_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_seminar_free_apply);
      sKeys.put("layout/fragment_servicecenter_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_servicecenter);
      sKeys.put("layout/fragment_withdrawal_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.fragment_withdrawal);
      sKeys.put("layout/item_myprofile_career_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_career);
      sKeys.put("layout/item_myprofile_edu_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_edu);
      sKeys.put("layout/item_myprofile_sns_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_myprofile_sns);
      sKeys.put("layout/item_someoneprofile_career_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_career);
      sKeys.put("layout/item_someoneprofile_edu_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_edu);
      sKeys.put("layout/item_someoneprofile_sns_0", com.garamgaebi.garamgaebi.garamgaebi.R.layout.item_someoneprofile_sns);
    }
  }
}
