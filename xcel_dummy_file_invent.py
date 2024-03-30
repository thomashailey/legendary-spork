import math
import random
import xlsxwriter

inventory_id_list = {'S':[350,'Solder', 'Lead-Based'],
                     'S':[350,'Solder', 'Lead-Free'],
                     'S':[350,'Solder', 'Rosin-Core'],
                     'S':[350,'Solder', 'Acid-Core'],
                     'S':[350,'Solder', 'Flux-Core'],
                     'S':[350,'Solder', 'Solid-Core'],
                     'S':[350,'Solder', 'Silver-Alloy'],
                     'S':[350,'Solder', 'Lead-Alloy'],
                     'M':[650,'Meter Replaceable Part','Wire Piercing Probes'],
                     'M':[650,'Meter Replaceable Part','Alligator Clips'],
                     'M':[650,'Meter Replaceable Part','Test Hook Clips'],
                     'M':[650,'Meter Replaceable Part','Test Tweezer'],
                     'M':[650,'Meter Replaceable Part','0.027 inch diameter,90 Degree Back Probe'],
                     'M':[650,'Meter Replaceable Part','0.027 inch diameter,135 Degree Back Probe'],
                     'M':[650,'Meter Replaceable Part','0.027 inch diameter,Straight Back Probe'],
                     'M':[650,'Meter Replaceable Part','0.040 inch diameter, 90 Degree Back Probe'],
                     'M':[650,'Meter Replaceable Part','0.040 inch diameter,135 Degree Back Probe'],
                     'M':[650,'Meter Replaceable Part','0.040 inch diameter,Straight Back Probe'],
                     'M':[650,'Meter Replaceable Part','nickel-plated copper pen tips'],
                     'M':[650,'Meter Replaceable Part','gold-plated copper pen tips'],
                     'M':[650,'Meter Replaceable Part','1KV CAT III 20A; 3ft/39inch Test Extension Lead'],
                     'M':[650,'Meter Replaceable Part','1000V CAT III 20A CE; 39inch Lead Extension'],
                     'M':[650,'Meter Replaceable Part','1000V CAT III 20A; Heavy Duty Test Probes'],
                     'M':[650,'Meter Replaceable Part','2MM Banana Jack To 4MM Banana Plug Adaptor'],
                     'M':[650,'Meter Replaceable Part','2MM Banana Jack To Back Probe Pin'],
                     'M':[650,'Meter Replaceable Part','tip shroud'],
                     'M':[650,'Meter Replaceable Part','Banana Plug to Alligator Clip Cable'],
                     'M':[650,'Meter Replaceable Part','Alligator Clip'],
                     'M':[650,'Meter Replaceable Part','Carrying Case'],
                     'M':[650,'Meter Replaceable Part','Roll-up Storage Pouch'],
                     'M':[650,'Meter Replaceable Part','Hanging Strap'],
                     'PWR':[650,'Power Supply Replacement Part','12V AC Adapter Power Supply'],
                     'PWR':[650,'Power Supply Replacement Part','9VDC 500Ma Power Adapter For ESP101 & ESP 102'],
                     'PWR':[650,'Power Supply Replacement Part','Shure PS60US Power Supply Energy Switching'],
                     'PWR':[650,'Power Supply Replacement Part','Power Supply Cord'],
                     'PWR':[650,'Power Supply Replacement Part','Power Supply Board'],
                     'PWR':[650,'Power Supply Replacement Part','Power Adapter']}
quantity_list = {'Used':['None'],
               'Available':['Primary','Secondary']}

inventoryList = []
employeeIdSet = set()
employeeCountNeeded = 8500
employeeCount = 0
numberTries=0
workbook = xlsxwriter.Workbook(f'InventoryListTest{numberTries}.xlsx')
worksheet = workbook.add_worksheet('Inventory')

row = 0
col = 0

class InventoryTable:
    def __init__(self, ItemID, ItemName, Description, Quantity, Location):
        self.ItemID = ItemID
        self.ItemName = ItemName
        self.Description = Description
        self.Quantity = Quantity
        self.Location = Location
        
    def __str__(self):
        return f"{self.ItemID}|{self.ItemName}|{self.Description}|{self.Quantity}|{self.Location}"
    
    def __int__(self):
        return int(self.ItemID, self)

def getQuantity():
    coinToss = random.randint(0,50)
    if coinToss %2 == 0:
        return 'Used'
    else:
        return 'Available'
    

def getLocation(currentQuantity):
    coinToss = random.randint(0,50)
    if currentQuantity == 'Used':
        return 'None'
    elif coinToss %2 == 0 and currentQuantity == 'Available':
        return 'Primary'
    else:
        return 'Secondary'

bold = workbook.add_format({'bold':True})
worksheet.write(row, col, 'ITEMID', bold)
worksheet.write(row, col+1, 'ITEMNAME', bold)
worksheet.write(row, col+2, 'DESCRIPTION', bold)
worksheet.write(row, col+3, 'QUANTITY', bold)
worksheet.write(row, col+4, 'LOCATON', bold)
row+=1

for key in inventory_id_list:
    idnumber=1
    while idnumber<=inventory_id_list[key][0]:
        fullId=[]
        quantity =  getQuantity()
        fullId.append(key)
        fullId.append('{:03d}'.format(idnumber))       
        invent = InventoryTable(''.join(fullId), inventory_id_list[key][1],  inventory_id_list[key][2], quantity, getLocation(quantity))

        inventoryList.append(invent)
        idnumber+=1
        strInvent = [x for x in str(invent).split('|')]
        for item in strInvent:
            worksheet.write(row, col, item)
            col +=1
        col=0
        row+=1
    
#print(employeeList)
#for _ in employeeList:
    #print(_)
#    print(str(_))

workbook.close()
