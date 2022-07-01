/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.view.inputmethod;

import android.annotation.AnyThread;
import android.annotation.NonNull;
import android.annotation.UserIdInt;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.window.ImeOnBackInvokedDispatcher;

import com.android.internal.inputmethod.DirectBootAwareness;
import com.android.internal.inputmethod.IInputMethodClient;
import com.android.internal.inputmethod.IRemoteAccessibilityInputConnection;
import com.android.internal.inputmethod.IRemoteInputConnection;
import com.android.internal.inputmethod.InputBindResult;
import com.android.internal.inputmethod.SoftInputShowHideReason;
import com.android.internal.inputmethod.StartInputFlags;
import com.android.internal.inputmethod.StartInputReason;
import com.android.internal.view.IInputMethodManager;

import java.util.List;

/**
 * A wrapper class to invoke IPCs defined in {@link IInputMethodManager}.
 */
final class IInputMethodManagerInvoker {
    @NonNull
    private final IInputMethodManager mTarget;

    private IInputMethodManagerInvoker(@NonNull IInputMethodManager target) {
        mTarget = target;
    }

    @AnyThread
    @NonNull
    static IInputMethodManagerInvoker create(@NonNull IInputMethodManager imm) {
        return new IInputMethodManagerInvoker(imm);
    }

    @AnyThread
    void addClient(IInputMethodClient client, IRemoteInputConnection fallbackInputConnection,
            int untrustedDisplayId) {
        try {
            mTarget.addClient(client, fallbackInputConnection, untrustedDisplayId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    List<InputMethodInfo> getInputMethodList(@UserIdInt int userId) {
        try {
            return mTarget.getInputMethodList(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    List<InputMethodInfo> getAwareLockedInputMethodList(@UserIdInt int userId,
            @DirectBootAwareness int directBootAwareness) {
        try {
            return mTarget.getAwareLockedInputMethodList(userId, directBootAwareness);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    List<InputMethodInfo> getEnabledInputMethodList(@UserIdInt int userId) {
        try {
            return mTarget.getEnabledInputMethodList(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    List<InputMethodSubtype> getEnabledInputMethodSubtypeList(String imiId,
            boolean allowsImplicitlySelectedSubtypes) {
        try {
            return mTarget.getEnabledInputMethodSubtypeList(imiId,
                    allowsImplicitlySelectedSubtypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    InputMethodSubtype getLastInputMethodSubtype() {
        try {
            return mTarget.getLastInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    boolean showSoftInput(IInputMethodClient client, IBinder windowToken,
            int flags, ResultReceiver resultReceiver, @SoftInputShowHideReason int reason) {
        try {
            return mTarget.showSoftInput(client, windowToken, flags, resultReceiver, reason);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    boolean hideSoftInput(IInputMethodClient client, IBinder windowToken,
            int flags, ResultReceiver resultReceiver, @SoftInputShowHideReason int reason) {
        try {
            return mTarget.hideSoftInput(client, windowToken, flags, resultReceiver, reason);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    InputBindResult startInputOrWindowGainedFocus(@StartInputReason int startInputReason,
            IInputMethodClient client, IBinder windowToken,
            @StartInputFlags int startInputFlags,
            @android.view.WindowManager.LayoutParams.SoftInputModeFlags int softInputMode,
            int windowFlags, EditorInfo editorInfo, IRemoteInputConnection remoteInputConnection,
            IRemoteAccessibilityInputConnection remoteAccessibilityInputConnection,
            int unverifiedTargetSdkVersion, ImeOnBackInvokedDispatcher imeDispatcher) {
        try {
            return mTarget.startInputOrWindowGainedFocus(startInputReason, client, windowToken,
                    startInputFlags, softInputMode, windowFlags, editorInfo, remoteInputConnection,
                    remoteAccessibilityInputConnection, unverifiedTargetSdkVersion, imeDispatcher);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void showInputMethodPickerFromClient(IInputMethodClient client, int auxiliarySubtypeMode) {
        try {
            mTarget.showInputMethodPickerFromClient(client, auxiliarySubtypeMode);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void showInputMethodPickerFromSystem(IInputMethodClient client, int auxiliarySubtypeMode,
            int displayId) {
        try {
            mTarget.showInputMethodPickerFromSystem(client, auxiliarySubtypeMode, displayId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void showInputMethodAndSubtypeEnablerFromClient(IInputMethodClient client, String imeId) {
        try {
            mTarget.showInputMethodAndSubtypeEnablerFromClient(client, imeId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    boolean isInputMethodPickerShownForTest() {
        try {
            return mTarget.isInputMethodPickerShownForTest();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    InputMethodSubtype getCurrentInputMethodSubtype() {
        try {
            return mTarget.getCurrentInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void setAdditionalInputMethodSubtypes(String imeId, InputMethodSubtype[] subtypes) {
        try {
            mTarget.setAdditionalInputMethodSubtypes(imeId, subtypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    int getInputMethodWindowVisibleHeight(IInputMethodClient client) {
        try {
            return mTarget.getInputMethodWindowVisibleHeight(client);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void reportVirtualDisplayGeometryAsync(IInputMethodClient client, int childDisplayId,
            float[] matrixValues) {
        try {
            mTarget.reportVirtualDisplayGeometryAsync(client, childDisplayId, matrixValues);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void reportPerceptibleAsync(IBinder windowToken, boolean perceptible) {
        try {
            mTarget.reportPerceptibleAsync(windowToken, perceptible);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void removeImeSurfaceFromWindowAsync(IBinder windowToken) {
        try {
            mTarget.removeImeSurfaceFromWindowAsync(windowToken);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    void startStylusHandwriting(IInputMethodClient client) {
        try {
            mTarget.startStylusHandwriting(client);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @AnyThread
    boolean isStylusHandwritingAvailableAsUser(@UserIdInt int userId) {
        try {
            return mTarget.isStylusHandwritingAvailableAsUser(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
