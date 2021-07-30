public enum AvailableTools {
    LADW(ToolTypes.LADDER, "Werner"),
    CHNS(ToolTypes.CHAINSAW, "Stihl"),
    JAKR(ToolTypes.JACKHAMMER, "Ridgid"),
    JAKD(ToolTypes.JACKHAMMER, "DeWalt")
    ;

    AvailableTools(ToolTypes toolTypes, String brandName) {
        this.toolTypes = toolTypes;
        this.brandName = brandName;
    }

    private final ToolTypes toolTypes;
    private final String brandName;

    public ToolTypes getToolTypes() {
        return toolTypes;
    }

    public String getBrandName() {
        return brandName;
    }
}
