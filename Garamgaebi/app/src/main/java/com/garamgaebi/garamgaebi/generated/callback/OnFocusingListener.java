package com.garamgaebi.garamgaebi.generated.callback;
public final class OnFocusingListener implements com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction.OnFocusingListener {
    final Listener mListener;
    final int mSourceId;
    public OnFocusingListener(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public void onFocusing(boolean callbackArg_0) {
        mListener._internalCallbackOnFocusing(mSourceId , callbackArg_0);
    }
    public interface Listener {
        void _internalCallbackOnFocusing(int sourceId , boolean callbackArg_0);
    }
}