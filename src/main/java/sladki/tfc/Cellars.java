package sladki.tfc;

import net.minecraftforge.common.MinecraftForge;
import ru.exsdev.ModManagerReworked;
import sladki.tfc.Handlers.ChunkEventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Cellars.MODNAME, modid = Cellars.MODID, version = Cellars.VERSION, dependencies = "after:terrafirmacraft")
public class Cellars
{
	public static final String MODID = "cellarsreworked";
	public static final String MODNAME = "TFC Cellars Reworked";
	public static final String VERSION = "${version}";

	@Instance(MODID)
	public static Cellars instance;

	@SidedProxy(clientSide = "sladki.tfc.ClientProxy", serverSide = "sladki.tfc.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModConfig.loadConfig(event);
	}

	@EventHandler
	public void initialize(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());

		ModManager.loadBlocks();
		ModManager.registerBlocks();

		ModManager.loadItems();
		ModManager.registerItems();

		ModManager.registerTileEntities();
		ModManager.registerRecipes();
		
		ModManagerReworked.loadBlocks();
		ModManagerReworked.registerBlocks();
		
		ModManagerReworked.registerTileEntities();

		proxy.registerRenderInformation();
		proxy.registerGuiHandler();
		proxy.hideItemsNEI();
	}

}
