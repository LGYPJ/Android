package com.garamgaebi.garamgaebi.generated.callback;
public final class OnFocusLostListener implements com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusLostListener {
    final Listener mListener;
    final int mSourceId;
    public OnFocusLostListener(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public void onFocusLost(android.widget.EditText callbackArg_0, boolean callbackArg_1) {
        mListener._internalCallbackOnFocusLost(mSourceId , callbackArg_0, callbackArg_1);
    }
    public interface Listener {
        void _internalCallbackOnFocusLost(int sourceId , android.widget.EditText callbackArg_0, boolean callbackArg_1);
    }
}