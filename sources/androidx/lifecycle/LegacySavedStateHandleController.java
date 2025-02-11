package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LegacySavedStateHandleController {
    static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";

    private LegacySavedStateHandleController() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SavedStateHandleController create(SavedStateRegistry registry, Lifecycle lifecycle, String key, Bundle defaultArgs) {
        Bundle restoredState = registry.consumeRestoredStateForKey(key);
        SavedStateHandle handle = SavedStateHandle.createHandle(restoredState, defaultArgs);
        SavedStateHandleController controller = new SavedStateHandleController(key, handle);
        controller.attachToLifecycle(registry, lifecycle);
        tryToAddRecreator(registry, lifecycle);
        return controller;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        OnRecreation() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void onRecreated(SavedStateRegistryOwner owner) {
            if (!(owner instanceof ViewModelStoreOwner)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
            }
            ViewModelStore viewModelStore = ((ViewModelStoreOwner) owner).getViewModelStore();
            SavedStateRegistry savedStateRegistry = owner.getSavedStateRegistry();
            for (String key : viewModelStore.keys()) {
                ViewModel viewModel = viewModelStore.get(key);
                LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, savedStateRegistry, owner.getLifecycle());
            }
            if (!viewModelStore.keys().isEmpty()) {
                savedStateRegistry.runOnNextRecreation(OnRecreation.class);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry registry, Lifecycle lifecycle) {
        SavedStateHandleController controller = (SavedStateHandleController) viewModel.getTag(TAG_SAVED_STATE_HANDLE_CONTROLLER);
        if (controller != null && !controller.isAttached()) {
            controller.attachToLifecycle(registry, lifecycle);
            tryToAddRecreator(registry, lifecycle);
        }
    }

    private static void tryToAddRecreator(final SavedStateRegistry registry, final Lifecycle lifecycle) {
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState == Lifecycle.State.INITIALIZED || currentState.isAtLeast(Lifecycle.State.STARTED)) {
            registry.runOnNextRecreation(OnRecreation.class);
        } else {
            lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.lifecycle.LegacySavedStateHandleController.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        Lifecycle.this.removeObserver(this);
                        registry.runOnNextRecreation(OnRecreation.class);
                    }
                }
            });
        }
    }
}
