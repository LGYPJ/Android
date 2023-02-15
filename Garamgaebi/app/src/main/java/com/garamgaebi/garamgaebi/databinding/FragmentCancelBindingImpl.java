package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentCancelBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentCancelBindingImpl extends FragmentCancelBinding {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_cancel_apply_info, 2);
        sViewsWithIds.put(R.id.activity_cancel_title_tv, 3);
        sViewsWithIds.put(R.id.activity_cancel_date_tv, 4);
        sViewsWithIds.put(R.id.activity_cancel_date_detail_tv, 5);
        sViewsWithIds.put(R.id.activity_cancel_place_tv, 6);
        sViewsWithIds.put(R.id.activity_cancel_place_detail_tv, 7);
        sViewsWithIds.put(R.id.activity_cancel_pay_tv, 8);
        sViewsWithIds.put(R.id.activity_cancel_pay_detail_tv, 9);
        sViewsWithIds.put(R.id.activity_cancel_deadline_tv, 10);
        sViewsWithIds.put(R.id.activity_cancel_deadline_detail_tv, 11);
        sViewsWithIds.put(R.id.activity_cancel_name_tv, 12);
        sViewsWithIds.put(R.id.activity_cancel_nickname_tv, 13);
        sViewsWithIds.put(R.id.activity_cancel_phone_tv, 14);
        sViewsWithIds.put(R.id.activity_cancel_bank_tv, 15);
        sViewsWithIds.put(R.id.activity_cancel_bank_down_btn, 16);
        sViewsWithIds.put(R.id.activity_cancel_apply_btn, 17);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener activityCancelPayEtandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of item.inputAccount.getValue()
            //         is item.inputAccount.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(activityCancelPayEt);
            // localize variables for thread safety
            // item
            com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
            // item != null
            boolean itemJavaLangObjectNull = false;
            // item.inputAccount.getValue()
            java.lang.String itemInputAccountGetValue = null;
            // item.inputAccount != null
            boolean itemInputAccountJavaLangObjectNull = false;
            // item.inputAccount
            androidx.lifecycle.MutableLiveData<java.lang.String> itemInputAccount = null;



            itemJavaLangObjectNull = (item) != (null);
            if (itemJavaLangObjectNull) {


                itemInputAccount = item.getInputAccount();

                itemInputAccountJavaLangObjectNull = (itemInputAccount) != (null);
                if (itemInputAccountJavaLangObjectNull) {




                    itemInputAccount.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentCancelBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private FragmentCancelBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[17]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[2]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[16]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[9]
            , (android.widget.EditText) bindings[1]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            );
        this.activityCancelPayEt.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.item == variableId) {
            setItem((com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItemInputAccount((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItemInputAccount(androidx.lifecycle.MutableLiveData<java.lang.String> ItemInputAccount, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        com.garamgaebi.garamgaebi.garamgaebi.viewModel.ApplyViewModel item = mItem;
        androidx.lifecycle.MutableLiveData<java.lang.String> itemInputAccount = null;
        java.lang.String itemInputAccountGetValue = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (item != null) {
                    // read item.inputAccount
                    itemInputAccount = item.getInputAccount();
                }
                updateLiveDataRegistration(0, itemInputAccount);


                if (itemInputAccount != null) {
                    // read item.inputAccount.getValue()
                    itemInputAccountGetValue = itemInputAccount.getValue();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityCancelPayEt, itemInputAccountGetValue);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.activityCancelPayEt, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, activityCancelPayEtandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item.inputAccount
        flag 1 (0x2L): item
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}