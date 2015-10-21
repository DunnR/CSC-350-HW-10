import java.util.LinkedList;

class Register
{
	private String name;
	private String binaryValue;
	
	public Register(String name)
	{
		this.name = name;
		this.binaryValue = "00000000";
	}

	public String getBinaryValue() {
		return binaryValue;
	}

	public void setBinaryValue(String binaryValue) {
		this.binaryValue = binaryValue;
	}

	public String getName() {
		return name;
	}
}


class PreviousRegister
{
	private String name;
	private String binaryValue;
	
	public PreviousRegister(String name, String binaryValue)
	{
		this.name = name;
		this.binaryValue = binaryValue;
	}
	
	public String getBinaryValue() {
		return binaryValue;
	}
	
	public String getName() {
		return name;
	}

}

public class CPU 
{
	private PCB thePCB;
	private LinkedList<Register> theRegisters = new LinkedList<Register>();
	private LinkedList<PreviousRegister> prevRegisters = new LinkedList<PreviousRegister>();
	
	public CPU()
	{
		this.thePCB = null;
		this.theRegisters.add(new Register("AX"));
		this.theRegisters.add(new Register("BX"));
		this.theRegisters.add(new Register("CX"));
		this.theRegisters.add(new Register("DX"));
	}
	
	public void contextSwitchIn(PCB p)
	{
		for(Register r : this.theRegisters)
		{
			String val = p.getValueForRegister(r.getName());
			if(val != null)
			{
				r.setBinaryValue(val);
			}
		}
		this.thePCB = p;
	}
	
	public PCB contextSwitchOut()
	{
		PCB currPCB = this.thePCB;
		
		PreviousRegister pr = new PreviousRegister(currPCB.getValueForRegister(this.theRegisters.getFirst().getName()), currPCB.getValueForRegister(this.theRegisters.getFirst().getBinaryValue()));
		
		this.prevRegisters.addLast(pr);
		
		currPCB = null;
		
		return this.thePCB = currPCB;
		
	}
}
