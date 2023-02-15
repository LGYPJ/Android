package com.garamgaebi.garamgaebi.databinding;
import com.garamgaebi.garamgaebi.garamgaebi.R;
import com.garamgaebi.garamgaebi.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSomeoneprofileCareerBindingImpl extends ItemSomeoneprofileCareerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_myprofile_career_list_item_tv_Textperiod, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSomeoneprofileCareerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ItemSomeoneprofileCareerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[5]
            );
        this.activityMyprofileCareerListItemTvContent.setTag(null);
        this.activityMyprofileCareerListItemTvEndPeriod.setTag(null);
        this.activityMyprofileCareerListItemTvName.setTag(null);
        this.activityMyprofileCareerListItemTvStartPeriod.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            setItem((com.garamgaebi.garamgaebi.garamgaebi.model.CareerData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.garamgaebi.garamgaebi.garamgaebi.model.CareerData Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        java.lang.String itemEndDate = null;
        com.garamgaebi.garamgaebi.garamgaebi.model.CareerData item = mItem;
        boolean itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueString = false;
        java.lang.String itemIsWorking = null;
        java.lang.String itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueStringActivityMyprofileCareerListItemTvEndPeriodAndroidStringNowItemEndDate = null;
        java.lang.String itemCompany = null;
        java.lang.String itemPosition = null;
        java.lang.String itemStartDate = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.isWorking()
                    itemIsWorking = item.isWorking();
                    // read item.company
                    itemCompany = item.getCompany();
                    // read item.position
                    itemPosition = item.getPosition();
                    // read item.startDate
                    itemStartDate = item.getStartDate();
                }


                if (itemIsWorking != null) {
                    // read item.isWorking().equals(@android:string/trueString)
                    itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueString = itemIsWorking.equals(activityMyprofileCareerListItemTvEndPeriod.getResources().getString(R.string.trueString));
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueString) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x4L) != 0) {

                if (item != null) {
                    // read item.endDate
                    itemEndDate = item.getEndDate();
                }
        }

        if ((dirtyFlags & 0x3L) != 0) {

                // read item.isWorking().equals(@android:string/trueString) ? @android:string/now : item.endDate
                itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueStringActivityMyprofileCareerListItemTvEndPeriodAndroidStringNowItemEndDate = ((itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueString) ? (activityMyprofileCareerListItemTvEndPeriod.getResources().getString(R.string.now)) : (itemEndDate));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyprofileCareerListItemTvContent, itemPosition);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyprofileCareerListItemTvEndPeriod, itemIsWorkingEqualsActivityMyprofileCareerListItemTvEndPeriodAndroidStringTrueStringActivityMyprofileCareerListItemTvEndPeriodAndroidStringNowItemEndDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyprofileCareerListItemTvName, itemCompany);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activityMyprofileCareerListItemTvStartPeriod, itemStartDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
        flag 2 (0x3L): item.isWorking().equals(@android:string/trueString) ? @android:string/now : item.endDate
        flag 3 (0x4L): item.isWorking().equals(@android:string/trueString) ? @android:string/now : item.endDate
    flag mapping end*/
    //end
}