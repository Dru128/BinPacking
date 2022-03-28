package other

import Container
import Item

class ItemsToConts
{
    fun convertItemsToConts(items: MutableList<Item>): Array<Container>?
    {
        var containers = arrayOf<Container>()

        for (item in items)
        {
            if (item.cont == null) return null
            if (item.cont in containers)
                containers[containers.lastIndexOf(item.cont)].objects.add(item)
            else {
                containers += item.cont!!
                containers.last().objects.add(item)
            }
        }

        return containers
    }
}