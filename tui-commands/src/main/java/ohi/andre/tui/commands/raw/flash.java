package ohi.andre.tui.commands.raw;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import ohi.andre.tui.commands.AbstractCommand;
import ohi.andre.tui.commands.R;
import ohi.andre.tui.commands.parameters.Parameter;

public class flash implements AbstractCommand {
    private boolean flashEnabled = false;
    private CameraManager.TorchCallback torchCallback = null;

    @Override
    public String name() {
        return "flash";
    }

    @Override
    public String exec(Context context, Parameter[] parameters) {
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

            if(torchCallback == null) {
                torchCallback = new CameraManager.TorchCallback() {
                    @Override
                    public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
                        flashEnabled = enabled;
                    }
                };

                cameraManager.registerTorchCallback(torchCallback, new Handler(Looper.getMainLooper()));
            }

            try {
                // look for a camera with flashlight
                for(String id : cameraManager.getCameraIdList()) {
                    CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(id);
                    if(characteristics == null) continue;

                    if(characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                        flashEnabled = !flashEnabled;
                        cameraManager.setTorchMode(id, flashEnabled);

                        return null;
                    }
                }

                // if no camera with a flashlight attached is found, error
                return context.getString(R.string.error_occurred);
            } catch (Exception e) {
                e.printStackTrace();
                return context.getString(R.string.error_occurred);
            }
        } else {
            return context.getString(R.string.error_occurred);
        }
    }

    @Override
    public void dispose(Context context) {
        if(torchCallback != null) {
            CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            cameraManager.unregisterTorchCallback(torchCallback);
        }
    }

    @Override
    public Parameter[] parameters() {
        return null;
    }
}
