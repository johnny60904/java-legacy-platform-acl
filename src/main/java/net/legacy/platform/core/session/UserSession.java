package net.legacy.platform.core.session;

import net.legacy.platform.core.identity.ClientIdentity;
import net.legacy.platform.core.model.ActiveAsset;
import net.legacy.platform.core.model.DigitalInventory;
import net.legacy.platform.core.network.NetworkPayload;

import java.util.List;

/**
 * @deprecated
 * ATTENTION: In the actual production environment, this class is a notorious "God Class"
 * exceeding 7,500+ lines of spaghetti code. It violates the Single Responsibility Principle (SRP)
 * by tightly coupling with clusters, inventories, databases, and network pipelines.
 * <p>
 * This stub retains clean, decoupled method signatures exclusively to allow the
 * Anti-Corruption Layer (com.dxlan.acl) to compile and perform boundary defense.
 */
@Deprecated(since = "v1.0.0", forRemoval = false)
public final class UserSession {

    private int sessionProcessId = 1;
    private String sessionProcessName = "Char";

    private final ActiveAsset activeAsset = new ActiveAsset();

    private final ClientIdentity clientIdentity = new ClientIdentity();
    private final ClientConnection clientConnection = new ClientConnection();

    private final DigitalInventory activeDeployedRepository = new DigitalInventory();
    private final DigitalInventory hardwareRepository = new DigitalInventory();
    private final DigitalInventory consumableRepository = new DigitalInventory();
    private final DigitalInventory generalMaterialRepository = new DigitalInventory();
    private final DigitalInventory deploymentKitRepository = new DigitalInventory();
    private final DigitalInventory premiumServiceRepository = new DigitalInventory();
    private final DigitalInventory extensionModuleRepository = new DigitalInventory();

    public UserSession() {}

    public int getSessionProcessId() { return sessionProcessId; }

    public String getSessionProcessName() {
        return sessionProcessName;
    }

    public ActiveAsset getActiveAssetByIndex(final int activeAssetIndex) { return activeAsset; }

    public void setSessionProcessId(final int sessionProcessId) { this.sessionProcessId = sessionProcessId; }

    public void setSessionProcessName(final String sessionProcessName) { this.sessionProcessName = sessionProcessName; }

    public ClientIdentity getClientIdentity() {
        return clientIdentity;
    }

    public DigitalInventory getActiveDeployedRepository() { return activeDeployedRepository; }

    public DigitalInventory getHardwareRepository() {
        return hardwareRepository;
    }

    public DigitalInventory getConsumableRepository() {
        return consumableRepository;
    }

    public DigitalInventory getGeneralMaterialRepository() {
        return generalMaterialRepository;
    }

    public DigitalInventory getDeploymentKitRepository() {
        return deploymentKitRepository;
    }

    public DigitalInventory getPremiumServiceRepository() {
        return premiumServiceRepository;
    }

    public DigitalInventory getExtensionModuleRepository() {
        return extensionModuleRepository;
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void write(final NetworkPayload payload) { clientConnection.write(payload); }

    public void write(final List<NetworkPayload> payloads) { clientConnection.write(payloads); }

}
