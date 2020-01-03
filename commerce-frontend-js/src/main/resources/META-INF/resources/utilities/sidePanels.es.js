export const registeredPanels = new Map();

export function exposeSidePanel(id, callback = null) {
    if(callback) {
        registeredPanels.set(id, callback);
    } else {
        registeredPanels.delete(id);
    }
}

export function getSidePanelData(id) {
    return registeredPanels.get(id);
}

export function getOpenedSidePanel() {
    let openedSidePanel = null;

    registeredPanels.forEach((getData, id) => {
        const data = getData();

        if(data.visible) {
            openedSidePanel = {
                id,
                ...data
            };
        }
    });

    return openedSidePanel;
}