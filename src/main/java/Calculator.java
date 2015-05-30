
public class Calculator {

	public static float DisplayMin=100;
	public static float DisplayMax=0;
	public static float CoresMin=10;
	public static float CoresMax=0;
	public static float ProcessorMin=100000;
	public static float ProcessorMax=0;
	public static float CameraMin=100;
	public static float CameraMax=0;
	public static float BatteryMin=10000;
	public static float BatteryMax=0;
	public static float RamMin=100;
	public static float RamMax=0;
	public static float StorageMin=10000;
	public static float StorageMax=0;
	
	//public int Coeficient=100;	// współczynnik
	public static float displayPoints(float num)
	{
		if(DisplayMax == DisplayMin) return 1;
		else
		return (num - DisplayMin)/(DisplayMax - DisplayMin);
	}
	public static float batteryPoints(float num)
	{
		if(BatteryMax == BatteryMin) return 1;
		else
		return (num - BatteryMin)/(BatteryMax - BatteryMin);
	}
	public static float coresPoints(float num)
	{
		if(CoresMax == CoresMin) return 1;
		else
		return (num - CoresMin)/(CoresMax - CoresMin);
	}
	public static float processorPoints(float num)
	{
		if(ProcessorMax == ProcessorMin) return 1;
		else
		return (num - ProcessorMin)/(ProcessorMax - ProcessorMin);
	}
	public static float cameraPoints(float num)
	{
		if(CameraMax == CameraMin) return 1;
		else
		return (num - CameraMin)/(CameraMax - CameraMin);
	}
	public static float ramPoints(float num)
	{
		if(RamMax == RamMin) return 1;
		else
		return (num - RamMin)/(RamMax - RamMin);
	}
	public static float storagePoints(float num)
	{
		if(StorageMax == StorageMin) return 1;
		else
		return (num - StorageMin)/(StorageMax - StorageMin);
	}
}
