package fi.dy.masa.malilib.gui.widget.list.header;

import java.util.List;
import java.util.function.Supplier;
import fi.dy.masa.malilib.gui.icon.BaseIcon;
import fi.dy.masa.malilib.gui.widget.button.GenericButton;
import fi.dy.masa.malilib.gui.widget.list.DataListWidget;
import fi.dy.masa.malilib.gui.widget.list.entry.DataListHeaderWidget;

public class BaseDataListEditHeaderWidget<DATATYPE> extends DataListHeaderWidget<DATATYPE>
{
    protected final List<DATATYPE> dataList;
    protected final Supplier<DATATYPE> dataFactory;
    protected final GenericButton addButton;

    public BaseDataListEditHeaderWidget(int x, int y, int width, int height,
                                        DataListWidget<DATATYPE> listWidget, String buttonHover,
                                        Supplier<DATATYPE> dataFactory)
    {
        super(x, y, 15, 15, listWidget);

        // This is a reference to the current entries list, which can be modified
        this.dataList = listWidget.getCurrentEntries();
        this.dataFactory = dataFactory;

        this.addButton = new GenericButton(x, y, BaseIcon.PLUS, buttonHover);
        this.addButton.setRenderOutline(true);
        this.addButton.setActionListener((btn, mbtn) -> this.insertEntry());
    }

    @Override
    public void reAddSubWidgets()
    {
        super.reAddSubWidgets();

        this.addWidget(this.addButton);
    }

    @Override
    public void updateSubWidgetsToGeometryChanges()
    {
        super.updateSubWidgetsToGeometryChanges();

        this.addButton.setPosition(this.getX(), this.getY());
    }

    protected void insertEntry()
    {
        this.dataList.add(0, this.dataFactory.get());
        this.listWidget.refreshEntries();
        this.listWidget.focusWidget(0);
    }
}